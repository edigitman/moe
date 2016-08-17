package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.StudExamAnswerDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.StudentExamAnswer;
import ro.agitman.moe.web.dto.StudentItemAnswerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d-uu31cq on 25.07.2016.
 */
public class StudExamAnswerDaoImpl extends GenericDaoImpl<StudentExamAnswer> implements StudExamAnswerDao {

    /**
     * @param beanSession .
     */
    public StudExamAnswerDaoImpl(BeanSession beanSession) {
        super(StudentExamAnswer.class, beanSession);
    }

    @Override
    public StudentExamAnswer findById(Integer id) {
        StudentExamAnswer studentExamAnswer = new StudentExamAnswer(id);
        boolean loaded = beanSession.load(studentExamAnswer);
        if (!loaded) {
            throw new IllegalStateException("Cannot load studentExamAnswer by id " + id);
        }
        return studentExamAnswer;
    }

    public List<StudentExamAnswer> findByStudent(Integer studId) {
        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(studId);

        return beanSession.loadList(answer);
    }

    public List<StudentExamAnswer> findBySExi(Integer studentExamInstanceId) {
        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setStudentExamInstanceId(studentExamInstanceId);

        return beanSession.loadList(answer);
    }

    public List<StudentItemAnswerDTO> findWithItemByStudentInstance(Integer studExiId){
        List<StudentItemAnswerDTO> result = new ArrayList<>();

        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            StringBuilder query = new StringBuilder(256);
            query.append("select ");
            query.append(beanSession.buildSelect(StudentExamAnswer.class, "answer"));
            query.append(", ");
            query.append(beanSession.buildSelect(ExamItem.class, "item"));
            query.append(" from student_exam_answers answer, exam_items item ");
            query.append(" where answer.examitemid = item.id and answer.studentexaminstanceid = ? ");

            stmt = SQLUtils.prepare(conn, query.toString(), studExiId);
            rset = stmt.executeQuery();

            while(rset.next()){

                StudentExamAnswer answer = new StudentExamAnswer();
                beanSession.populateBean(rset, answer, "answer");

                ExamItem item = new ExamItem();
                beanSession.populateBean(rset, item, "item");

                result.add(new StudentItemAnswerDTO(answer, item));
            }

            return result;

        } catch (SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(rset, stmt);
        }
    }

    public StudentExamAnswer findByExiStudItem(Integer studId, Integer itemId) {
        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(studId);
        answer.setExamItemId(itemId);

        return beanSession.loadUnique(answer);
    }

    public List<StudentExamAnswer> findByStudentAndExId(Integer studId, Integer instanceId){
        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(studId);
        answer.setStudentExamInstanceId(instanceId);

        return beanSession.loadList(answer);
    }
}
