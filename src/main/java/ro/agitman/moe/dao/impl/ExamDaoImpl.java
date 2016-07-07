package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.model.Exam;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamDaoImpl extends GenericDaoImpl<Exam> implements ExamDao {


    public ExamDaoImpl(BeanSession beanSession) {
        super(Exam.class, beanSession);
    }
}
