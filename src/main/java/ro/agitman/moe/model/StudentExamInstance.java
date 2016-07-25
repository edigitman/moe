package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class StudentExamInstance {

    private Integer id;
    private Integer examid;
    private Integer studid;
    private Integer status;
    private Timestamp datecreated;
    private Timestamp dateupdated;

    public StudentExamInstance(Integer id) {
        this.id = id;
    }

    public StudentExamInstance() {
    }

    @Override
    public String toString() {
        return "StudentExamInstance{" +
                "id=" + id +
                ", examid=" + examid +
                ", studid=" + studid +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentExamInstance that = (StudentExamInstance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (examid != null ? !examid.equals(that.examid) : that.examid != null) return false;
        if (studid != null ? !studid.equals(that.studid) : that.studid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (datecreated != null ? !datecreated.equals(that.datecreated) : that.datecreated != null) return false;
        return dateupdated != null ? dateupdated.equals(that.dateupdated) : that.dateupdated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (examid != null ? examid.hashCode() : 0);
        result = 31 * result + (studid != null ? studid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        result = 31 * result + (dateupdated != null ? dateupdated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public Integer getStudid() {
        return studid;
    }

    public void setStudid(Integer studid) {
        this.studid = studid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public Timestamp getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(Timestamp dateupdated) {
        this.dateupdated = dateupdated;
    }
}
