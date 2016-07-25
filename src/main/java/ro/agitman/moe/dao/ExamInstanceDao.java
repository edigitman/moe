package ro.agitman.moe.dao;

import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamInstance;
import ro.agitman.moe.web.dto.ExamInstanceDTO;

import java.util.List;

/**
 * Created by d-uu31cq on 20.07.2016.
 */
public interface ExamInstanceDao extends GenericDao<ExamInstance>{

    List<ExamInstance> findByOwner(Integer id);

    List<ExamInstance> findByStudent(Integer studId);

    List<ExamInstanceDTO> findByOwnerToDTO(Integer id);
}
