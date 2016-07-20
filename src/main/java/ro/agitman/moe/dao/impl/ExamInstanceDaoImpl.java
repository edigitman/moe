package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamInstanceDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamInstance;

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

    public List<ExamInstance> findByOwner(Integer id){
        ExamInstance instance = new ExamInstance();
        instance.setOwner(id);
        return beanSession.loadList(instance);
    }
}
