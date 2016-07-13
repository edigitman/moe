package ro.agitman.moe.dao;

import ro.agitman.moe.model.ExamItemAnswer;

import java.util.List;

/**
 * Created by d-uu31cq on 13.07.2016.
 */
public interface ExamItemAnswerDao extends GenericDao<ExamItemAnswer> {

    List<ExamItemAnswer> findForItem(Integer id);

}
