package ro.agitman.moe.dao;

import ro.agitman.moe.model.StudentExamInstance;

import java.util.List;

/**
 * Created by d-uu31cq on 25.07.2016.
 */
public interface StudExamInstanceDao extends GenericDao<StudentExamInstance> {

    List<StudentExamInstance> findByOwner(Integer id);

    StudentExamInstance findActiveByOwner(Integer id);

    void updateExamInstanceStatusById(Integer id);
}
