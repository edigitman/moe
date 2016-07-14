package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class Exam {

    private Integer id;
    private String name;
    private Integer owner;
    private Integer difficulty;
    private Long points;
    private Timestamp datecreated;

    public Exam() {
    }

    public Exam(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;

        if (!id.equals(exam.id)) return false;
        if (!name.equals(exam.name)) return false;
        if (!owner.equals(exam.owner)) return false;
        if (difficulty != null ? !difficulty.equals(exam.difficulty) : exam.difficulty != null) return false;
        return datecreated != null ? datecreated.equals(exam.datecreated) : exam.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
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

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
