package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.ExamInstanceDao;
import ro.agitman.moe.dao.PojoMapper;
import ro.agitman.moe.model.ExamInstance;
import ro.agitman.moe.web.dto.ExamInstanceDTO;

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
            query.append(" where groupuser.studentid = ? and inst.status > 1");

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


    public List<ExamInstanceDTO> findByOwnerToDTO(Integer id){
        List<ExamInstanceDTO> result = new ArrayList<>();

        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            StringBuilder query = new StringBuilder(256);
            query.append("select ");
            query.append(" inst.id as id, inst.name as name, inst.status as status, " +
                    "inst.startdate as startdate, g.name as groupName, e.name as examName");
            query.append(" from exam_instances inst");
            query.append(" join exam_groups g on inst.egroupid = g.id");
            query.append(" join exams e on inst.examid = e.id");
            query.append(" where inst.owner = ?");

            stmt = SQLUtils.prepare(conn, query.toString(), id);
            rset = stmt.executeQuery();

            PojoMapper<ExamInstanceDTO> pojoMapper = new PojoMapper(ExamInstanceDTO.class);

            result = pojoMapper.mapRersultSetToObject(rset);

//            while (rset.next()) {
//                ExamInstanceDTO instance = new ExamInstanceDTO();
//                beanSession.populateBean(rset, instance);
//                result.add(instance);
//            }

            return result;
        } catch (SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(rset, stmt);
        }
    }

}
