package ro.agitman.moe.web.dto;

import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.StudentExamAnswer;

/**
 * Created by edi on 8/5/16.
 */
public class StudentItemAnswerDTO {

    private final StudentExamAnswer answer;
    private final ExamItem item;

    public StudentItemAnswerDTO(StudentExamAnswer answer, ExamItem item) {
        this.answer = answer;
        this.item = item;
    }

    public StudentExamAnswer getAnswer() {
        return answer;
    }

    public ExamItem getItem() {
        return item;
    }
}
