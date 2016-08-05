package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamGroup {

    private Integer id;
    private String  name;
    private Integer students;
    private Integer owner;
    private Boolean locked;
    private Timestamp datecreated;

    public ExamGroup(Integer id) {
        this.id = id;
    }

    public ExamGroup() {
    }

    @Override
    public String toString() {
        return "ExamGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamGroup examGroup = (ExamGroup) o;

        if (id != null ? !id.equals(examGroup.id) : examGroup.id != null) return false;
        if (name != null ? !name.equals(examGroup.name) : examGroup.name != null) return false;
        if (owner != null ? !owner.equals(examGroup.owner) : examGroup.owner != null) return false;
        return datecreated != null ? datecreated.equals(examGroup.datecreated) : examGroup.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
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

    public Integer getStudents() {
        return students;
    }

    public void setStudents(Integer students) {
        this.students = students;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
