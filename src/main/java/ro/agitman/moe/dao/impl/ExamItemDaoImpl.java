package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.User;

import java.util.List;

/**
 * Created by edi on 7/11/2016.
 */
public class ExamItemDaoImpl extends GenericDaoImpl<ExamItem> implements ExamItemDao {

    public ExamItemDaoImpl(BeanSession beanSession) {
        super(ExamItem.class, beanSession);
    }

    @Override
    public List<ExamItem> findByExamId(Integer examId) {
        ExamItem examItem = new ExamItem();
        examItem.setExamid(examId);

        return beanSession.loadList(examItem);
    }

    @Override
    public ExamItem findById(Integer id) {
        ExamItem examItem = new ExamItem(id);
        boolean loaded = beanSession.load(examItem);
        if (!loaded) {
            throw new IllegalStateException("Cannot load examItem by id " + id);
        }
        return examItem;
    }
}
