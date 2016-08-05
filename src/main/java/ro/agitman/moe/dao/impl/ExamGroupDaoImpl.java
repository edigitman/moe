package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.ExamGroupDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by d-uu31cq on 15.07.2016.
 */
public class ExamGroupDaoImpl extends GenericDaoImpl<ExamGroup> implements ExamGroupDao {
    /**
     * @param beanSession .
     */
    public ExamGroupDaoImpl(BeanSession beanSession) {
        super(ExamGroup.class, beanSession);
    }

    public List<ExamGroup> findByOwner(Integer id){
        ExamGroup examGroup = new ExamGroup();
        examGroup.setOwner(id);

        return beanSession.loadList(examGroup);
    }

    public void updateStudentsNo(Integer groupId){
        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        try {
            StringBuilder query = new StringBuilder(256);
            query.append("update exam_groups set students = ( select count (*) from exam_group_user where groupid = ? ) where id = ? ");

            stmt = SQLUtils.prepare(conn, query.toString(), groupId, groupId);
            stmt.executeUpdate();

        } catch(SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(stmt);
        }
    }

    @Override
    public ExamGroup findById(Integer id) {
        ExamGroup examGroup = new ExamGroup(id);
        boolean loaded = beanSession.load(examGroup);
        if (!loaded) {
            throw new IllegalStateException("Cannot load examGroup by id " + id);
        }
        return examGroup;
    }
}
