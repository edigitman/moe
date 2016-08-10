package ro.agitman.moe.dao;

import ro.agitman.moe.model.ExamItem;

import java.util.List;

/**
 * Created by edi on 7/11/2016.
 */
public interface ExamItemDao extends GenericDao<ExamItem> {

    List<ExamItem> findByExamId(Integer examId);

    Integer findNextOrderIndex(Integer examId);

}
