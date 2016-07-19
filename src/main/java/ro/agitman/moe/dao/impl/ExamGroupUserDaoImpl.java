package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.ExamGroupUserDao;
import ro.agitman.moe.dao.GenericDao;
import ro.agitman.moe.model.ExamGroupUser;
import ro.agitman.moe.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by edi on 7/19/2016.
 */
public class ExamGroupUserDaoImpl extends GenericDaoImpl<ExamGroupUser> implements ExamGroupUserDao {
    /**
     * @param beanSession .
     */
    public ExamGroupUserDaoImpl(BeanSession beanSession) {
        super(ExamGroupUser.class, beanSession);
    }

    @Override
    public ExamGroupUser findById(Integer id) {
        ExamGroupUser groupUser = new ExamGroupUser(id);
        boolean loaded = beanSession.load(groupUser);
        if (!loaded) {
            throw new IllegalStateException("Cannot load groupUser by id " + id);
        }
        return groupUser;
    }

    public void deleteByKeys(Integer studId, Integer groupId){
        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        try {
            StringBuilder query = new StringBuilder(256);
            query.append("delete from exam_group_user g ");
            query.append("where g.groupid = ? and g.studentid = ?");

            stmt = SQLUtils.prepare(conn, query.toString(), groupId, studId);
            stmt.executeUpdate();

        } catch(SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(stmt);
        }
    }

    @Override
    public List<User> findByGroupId(Integer groupId) {

        List<User> result = new ArrayList<>();
        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            StringBuilder query = new StringBuilder(256);
            query.append("select ");
            query.append(beanSession.buildSelect(User.class, "u"));
            query.append(" from users u join exam_group_user gu on u.id = gu.studentid");
            query.append(" where gu.groupid = ?");

            stmt = SQLUtils.prepare(conn, query.toString(), groupId);
            rset = stmt.executeQuery();

           while (rset.next()) {
                User user = new User();
                beanSession.populateBean(rset, user, "u");
                result.add(user);
            }

            return result;
        } catch(SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(rset, stmt);
        }
    }
}
