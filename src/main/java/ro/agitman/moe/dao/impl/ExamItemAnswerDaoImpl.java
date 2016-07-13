package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamItemAnswerDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItemAnswer;

import java.util.List;

/**
 * Created by d-uu31cq on 13.07.2016.
 */
public class ExamItemAnswerDaoImpl extends GenericDaoImpl<ExamItemAnswer> implements ExamItemAnswerDao {


    /**
     * @param beanSession .
     */
    public ExamItemAnswerDaoImpl(BeanSession beanSession) {
        super(ExamItemAnswer.class, beanSession);
    }

    @Override
    public ExamItemAnswer findById(Integer id) {
        ExamItemAnswer answer = new ExamItemAnswer(id);
        boolean loaded = beanSession.load(answer);
        if (!loaded) {
            throw new IllegalStateException("Cannot load answer by id " + id);
        }
        return answer;
    }

    @Override
    public List<ExamItemAnswer> findForItem(Integer id) {

        ExamItemAnswer answer = new ExamItemAnswer();
        answer.setItemid(id);

        return beanSession.loadList(answer);
    }
}
