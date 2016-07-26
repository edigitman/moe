package ro.agitman.moe.dao;

import ro.agitman.moe.model.StudentExamAnswer;

import java.util.List;

/**
 * Created by d-uu31cq on 25.07.2016.
 */
public interface StudExamAnswerDao extends GenericDao<StudentExamAnswer> {

    List<StudentExamAnswer> findByStudent(Integer studId);

    StudentExamAnswer findByExiStudItem(Integer exiId, Integer studId, Integer itemId);
}
