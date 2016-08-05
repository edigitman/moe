package ro.agitman.moe.service;

import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.StudentExamAnswer;
import ro.agitman.moe.web.dto.StudentItemAnswerDTO;

import java.util.List;

/**
 * Created by edi on 8/5/16.
 */
public interface StudExamAnswerService {


    List<StudentExamAnswer> findByStudent(Integer id);

    List<StudentItemAnswerDTO> loadStudentItemAsnwers(Integer exiId);

    StudentExamAnswer saveInsert(StudentExamAnswer exam);

    StudentExamAnswer createAnswer(Object answerValue, Boolean skip,Integer ownerId, Integer studExiId, Integer itemId, Boolean isLast);
}
