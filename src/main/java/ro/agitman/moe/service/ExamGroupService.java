package ro.agitman.moe.service;

import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamGroup;

import java.util.List;

/**
 * Created by d-uu31cq on 05.08.2016.
 */
public interface ExamGroupService {

    List<ExamGroup> findByOwner(Integer id);

    ExamGroup saveInsert(Integer userId, ExamGroup exam);

    ExamGroup findById(Integer groupId);

    void updateStudentsNo(Integer groupId);

    void lockGroup(Integer groupId);
}
