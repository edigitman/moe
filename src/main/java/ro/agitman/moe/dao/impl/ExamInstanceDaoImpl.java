package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.ExamInstanceDao;
import ro.agitman.moe.model.ExamInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d-uu31cq on 20.07.2016.
 */
public class ExamInstanceDaoImpl extends GenericDaoImpl<ExamInstance> implements ExamInstanceDao {

    /**
     * @param beanSession .
     */
    public ExamInstanceDaoImpl(BeanSession beanSession) {
        super(ExamInstance.class, beanSession);
    }

    @Override
    public ExamInstance findById(Integer id) {
        ExamInstance instance = new ExamInstance(id);
        boolean loaded = beanSession.load(instance);
        if (!loaded) {
            throw new IllegalStateException("Cannot load exam instance by id " + id);
        }
        return instance;
    }

    public List<ExamInstance> findByOwner(Integer id) {
        ExamInstance instance = new ExamInstance();
        instance.setOwner(id);
        return beanSession.loadList(instance);
    }

    public List<ExamInstance> findByStudent(Integer studId) {

        List<ExamInstance> result = new ArrayList<>();

        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            StringBuilder query = new StringBuilder(256);
            query.append("select ");
            query.append(beanSession.buildSelect(ExamInstance.class, "inst"));
            query.append(" from exam_instances inst");
            query.append(" join exam_groups exgroup on inst.egroupid = exgroup.id");
            query.append(" join exam_group_user groupuser on groupuser.groupid = exgroup.id");
            query.append(" where groupuser.studentid = ?");

            stmt = SQLUtils.prepare(conn, query.toString(), studId);
            rset = stmt.executeQuery();

            while (rset.next()) {
                ExamInstance instance = new ExamInstance();
                beanSession.populateBean(rset, instance, "inst");
                result.add(instance);
            }

            return result;
        } catch (SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(rset, stmt);
        }
    }
}
