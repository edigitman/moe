package ro.agitman.moe.service.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.service.ExamService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by d-uu31cq on 05.08.2016.
 */
public class ExamServiceImpl implements ExamService {

    private final BeanSession session;
    private final ExamDao examDao;
    private final ExamItemDao examItemDao;

    public ExamServiceImpl(BeanSession beanSession, ExamDao examDao, ExamItemDao examItemDao) {
        this.session = beanSession;
        this.examDao = examDao;
        this.examItemDao = examItemDao;
    }

    @Override
    public List<Exam> findForOwner(Integer id) {
        return examDao.findForOwner(id);
    }

    @Override
    public Exam findById(Integer examId) {
        return examDao.findById(examId);
    }

    public void recomputeTotalPoints(Integer examId) {

        try {
            this.session.getConnection().setAutoCommit(false);

            Exam exam = examDao.findById(examId);
            List<ExamItem> items = examItemDao.findByExamId(examId);

            Integer total = items.stream().mapToInt(ExamItem::getPoints).sum();

            exam.setPoints(total);
            exam.setItems(items.size());
            examDao.save(exam);

            this.session.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNameDiff(Integer id, String value, String name) {
        try {
            session.getConnection().setAutoCommit(false);

            Exam exam = examDao.findById(id);
            if ("examName".equals(name))
                exam.setName(value);

            if ("examDiff".equals(name))
                exam.setDifficulty(Integer.valueOf(value));

            examDao.save(exam);

            session.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //TODO move to exam Instance service
    public void lockExam(Integer id){
        Exam exam = examDao.findById(id);
        exam.setLocked(true);
        examDao.save(exam);
    }

    public Exam saveInsert(Integer userId, Exam exam) {

        exam.setOwner(userId);
        if (exam.getId() == null) {
            examDao.insert(exam);
        } else {
            examDao.save(exam);
        }

        return exam;
    }

}
