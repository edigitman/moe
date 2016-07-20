package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamGroupDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamGroup;

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
