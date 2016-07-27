package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamInstance {

    private Integer id;
    private String name;
    // 1 - ciorna
    // 2 - pornit
    // 3 - inchis
    // 4 - spre corectare
    // 5 - corectat
    private Integer status;
    private Timestamp startdate;
    private Timestamp enddate;
    private Integer examid;
    private Integer egroupid;
    private Integer owner;
    private Boolean autoSolved;
    private Timestamp datecreated;

    public ExamInstance(Integer id) {
        this.id = id;
    }

    public ExamInstance() {
    }

    @Override
    public String toString() {
        return "ExamInstance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", startdate=" + startdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamInstance that = (ExamInstance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        if (examid != null ? !examid.equals(that.examid) : that.examid != null) return false;
        if (egroupid != null ? !egroupid.equals(that.egroupid) : that.egroupid != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + (examid != null ? examid.hashCode() : 0);
        result = 31 * result + (egroupid != null ? egroupid.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public Integer getEgroupid() {
        return egroupid;
    }

    public void setEgroupid(Integer egroupid) {
        this.egroupid = egroupid;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public Boolean getAutoSolved() {
        return autoSolved;
    }

    public void setAutoSolved(Boolean autoSolved) {
        this.autoSolved = autoSolved;
    }
}
