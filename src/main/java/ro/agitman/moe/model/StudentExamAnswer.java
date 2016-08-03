package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by edi on 7/7/2016.
 */
public class StudentExamAnswer {

    private Integer id;
    private Integer studentExamInstanceId;
    private Integer examItemId;
    private Integer ownerId;
    private String value;
    private String rawAnswer;
    private Boolean solvable;
    private Boolean reviewed;
    private Boolean correct;
    private Integer points;
    private Timestamp datecreated;

    public StudentExamAnswer(Integer id) {
        this.id = id;
    }

    public StudentExamAnswer() {
    }

    @Override
    public String toString() {
        return "StudentExamAnswer{" +
                "id=" + id +
                ", studentExamInstanceId=" + studentExamInstanceId +
                ", examItemId=" + examItemId +
                ", ownerId=" + ownerId +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentExamAnswer that = (StudentExamAnswer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (studentExamInstanceId != null ? !studentExamInstanceId.equals(that.studentExamInstanceId) : that.studentExamInstanceId != null)
            return false;
        if (examItemId != null ? !examItemId.equals(that.examItemId) : that.examItemId != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (studentExamInstanceId != null ? studentExamInstanceId.hashCode() : 0);
        result = 31 * result + (examItemId != null ? examItemId.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentExamInstanceId() {
        return studentExamInstanceId;
    }

    public void setStudentExamInstanceId(Integer studentExamInstanceId) {
        this.studentExamInstanceId = studentExamInstanceId;
    }

    public Integer getExamItemId() {
        return examItemId;
    }

    public void setExamItemId(Integer examItemId) {
        this.examItemId = examItemId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getRawAnswer() {
        return rawAnswer;
    }

    public void setRawAnswer(String rawAnswer) {
        this.rawAnswer = rawAnswer;
    }

    public Boolean getSolvable() {
        return solvable;
    }

    public void setSolvable(Boolean solvable) {
        this.solvable = solvable;
    }


    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewd) {
        this.reviewed = reviewd;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
