package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamItem {

    private Integer id;
    private String assertion;
    private Integer difficulty;
    private Long points;
    private String type;
    private Integer examid;
    private Timestamp datecreated;

    public ExamItem(Integer examId) {
        this.id = examId;
    }

    public ExamItem() {
    }

    @Override
    public String toString() {
        return "ExamItem{" +
                "id=" + id +
                ", assertion='" + assertion + '\'' +
                ", type=" + type +
                ", examid=" + examid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamItem examItem = (ExamItem) o;

        if (!id.equals(examItem.id)) return false;
        if (!assertion.equals(examItem.assertion)) return false;
        if (difficulty != null ? !difficulty.equals(examItem.difficulty) : examItem.difficulty != null) return false;
        if (points != null ? !points.equals(examItem.points) : examItem.points != null) return false;
        if (!type.equals(examItem.type)) return false;
        if (!examid.equals(examItem.examid)) return false;
        return !(datecreated != null ? !datecreated.equals(examItem.datecreated) : examItem.datecreated != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + assertion.hashCode();
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + examid.hashCode();
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }
}
