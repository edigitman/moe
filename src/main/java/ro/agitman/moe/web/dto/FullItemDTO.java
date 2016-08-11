package ro.agitman.moe.web.dto;

import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.ExamItemAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edi on 8/11/16.
 */
public class FullItemDTO {

    private Integer id;
    private String assertion;
    private Integer points;
    private Integer type;
    private Integer examid;
    private Integer ord;
    private List<ExamItemAnswer> answers = new ArrayList<>();

    public FullItemDTO(ExamItem item) {
        this.id = item.getId();
        this.assertion = item.getAssertion();
        this.points = item.getPoints();
        this.type = item.getType();
        this.examid = item.getExamid();
        this.ord = item.getOrd();
    }

    public Integer getId() {
        return id;
    }

    public String getAssertion() {
        return assertion;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getType() {
        return type;
    }

    public Integer getExamid() {
        return examid;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setAnswers(List<ExamItemAnswer> answers) {
        this.answers = answers;
    }

    public List<ExamItemAnswer> getAnswers() {
        return answers;
    }
}
