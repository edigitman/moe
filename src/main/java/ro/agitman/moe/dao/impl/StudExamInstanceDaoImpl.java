package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.StudExamInstanceDao;
import ro.agitman.moe.model.StudentExamInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by d-uu31cq on 25.07.2016.
 */
public class StudExamInstanceDaoImpl extends GenericDaoImpl<StudentExamInstance> implements StudExamInstanceDao {
    /**
     * @param beanSession .
     */
    public StudExamInstanceDaoImpl(BeanSession beanSession) {
        super(StudentExamInstance.class, beanSession);
    }

    @Override
    public StudentExamInstance findById(Integer id) {
        StudentExamInstance studentExamInstance = new StudentExamInstance(id);
        boolean loaded = beanSession.load(studentExamInstance);
        if (!loaded) {
            throw new IllegalStateException("Cannot load studentExamInstance by id " + id);
        }
        return studentExamInstance;
    }

    public List<StudentExamInstance> findByOwner(Integer id){
        StudentExamInstance instance = new StudentExamInstance();
        instance.setStudid(id);

        return beanSession.loadList(instance);
    }

    public void updateExamInstanceStatusById(Integer id){
        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            stmt = SQLUtils.prepare(conn, "update student_exam_instances set status = 3 where examid = ?", id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(stmt);
        }
    }

    public List<StudentExamInstance> findByExamInstanceId(Integer examid){
        StudentExamInstance instance = new StudentExamInstance();
        instance.setExamid(examid);

        return beanSession.loadList(instance);
    }

    public StudentExamInstance findActiveByOwnerAndExamInstance(Integer studId, Integer examId){
        StudentExamInstance instance = new StudentExamInstance();
        instance.setStudid(studId);
        instance.setExamid(examId);

        return beanSession.loadUnique(instance);
    }

    public StudentExamInstance findActiveByOwner(Integer id){
        StudentExamInstance instance = new StudentExamInstance();
        instance.setStudid(id);

        List<StudentExamInstance> result = beanSession.loadList(instance);
        for(StudentExamInstance inst : result){
            if(inst.getStatus() == 1){
                return inst;
            }
        }
        return null;
    }
}
