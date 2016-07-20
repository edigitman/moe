package ro.agitman.moe.dao;

import ro.agitman.moe.model.ExamInstance;

import java.util.List;

/**
 * Created by d-uu31cq on 20.07.2016.
 */
public interface ExamInstanceDao extends GenericDao<ExamInstance>{

    List<ExamInstance> findByOwner(Integer id);

}
