package ro.agitman.moe.dao;

import ro.agitman.moe.model.Exam;

import java.util.List;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public interface ExamDao extends GenericDao<Exam> {


    List<Exam> findForOwner(Integer id);

    Exam findForExamInstanceId(Integer exiId);
}
