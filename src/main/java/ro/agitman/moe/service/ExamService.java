package ro.agitman.moe.service;

import ro.agitman.moe.model.Exam;

import java.util.List;

/**
 * Created by d-uu31cq on 05.08.2016.
 */
public interface ExamService {

    List<Exam> findForOwner(Integer id);

    Exam findById(Integer examId);

    void recomputeTotalPoints(Integer examId);

    void updateNameDiff(Integer id, String value, String name);

    Exam saveInsert(Integer userId, Exam exam);

    void lockExam(Integer id);
}
