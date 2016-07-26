package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.StudExamAnswerDao;
import ro.agitman.moe.model.StudentExamAnswer;

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

    public StudentExamAnswer findByExiStudItem(Integer exiId, Integer studId, Integer itemId) {
        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(studId);
        answer.setExamItemId(exiId);
        answer.setExamItemId(itemId);

        return beanSession.loadUnique(answer);
    }
}
