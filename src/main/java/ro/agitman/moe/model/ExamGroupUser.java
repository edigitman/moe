package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamGroupUser {

    private Integer id;
    private Integer groupid;
    private Integer studentid;
    private Timestamp datecreated;

    public ExamGroupUser(Integer id) {
        this.id = id;
    }

    public ExamGroupUser() {
    }

    @Override
    public String toString() {
        return "ExamGroupUser{" +
                "id=" + id +
                ", groupid=" + groupid +
                ", studentid=" + studentid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamGroupUser that = (ExamGroupUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (studentid != null ? !studentid.equals(that.studentid) : that.studentid != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (studentid != null ? studentid.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }
}
