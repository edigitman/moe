package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamItem {

    private Integer id;
    private String assertion;
    private String title;
    private Integer difficulty;
    private Integer points;
    private Integer type;
    private Integer examid;
    private Integer ord;
    private Timestamp datecreated;

    public ExamItem(Integer examId) {
        this.id = examId;
    }

    public ExamItem() {
    }

    public ExamItem(ExamItem item) {
        this.assertion = item.getAssertion();
        this.title = item.getTitle();
        this.difficulty = item.getDifficulty();
        this.points = item.getPoints();
        this.type = item.getType();
        this.ord = item.getOrd();
    }

    @Override
    public String toString() {
        return "ExamItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
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
        result = 31 * result + title.hashCode();
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }
}
