package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamItemAnswer {

    private Integer id;
    private Boolean correct;
    private String value;
    private Integer itemid;
    private Timestamp datecreated;

    public ExamItemAnswer(Integer id) {
        this.id = id;
    }

    public ExamItemAnswer() {
    }

    @Override
    public String toString() {
        return "ExamItemAnswer{" +
                "id=" + id +
                ", correct=" + correct +
                ", value='" + value + '\'' +
                ", itemid=" + itemid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamItemAnswer that = (ExamItemAnswer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (correct != null ? !correct.equals(that.correct) : that.correct != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (itemid != null ? itemid.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }
}
