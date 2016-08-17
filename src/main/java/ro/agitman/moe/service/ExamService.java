package ro.agitman.moe.service;

import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;

import java.util.List;

/**
 * Created by d-uu31cq on 05.08.2016.
 */
public interface ExamService {

    Integer createExamItem(Integer examId, ExamItem examItem);

    void deleteItem(Integer itemId, Integer examId);

    List<Exam> findForOwner(Integer id);

    Exam findById(Integer examId);

    void updateNameDiff(Integer id, String value, String name);

    Exam saveInsert(Integer userId, Exam exam);

    void lockExam(Integer id);

    void createExam(Exam exam, Integer cloneId);
}
