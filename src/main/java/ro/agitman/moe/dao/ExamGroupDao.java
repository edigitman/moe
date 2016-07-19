package ro.agitman.moe.dao;

import ro.agitman.moe.model.ExamGroup;

import java.util.List;

/**
 * Created by d-uu31cq on 15.07.2016.
 */
public interface ExamGroupDao extends GenericDao<ExamGroup>{

    List<ExamGroup> findByOwner(Integer id);

    void updateStudents(Integer groupId);
}
