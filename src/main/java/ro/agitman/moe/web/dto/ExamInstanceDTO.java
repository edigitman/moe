package ro.agitman.moe.web.dto;

import ro.agitman.moe.dao.annotations.Column;
import ro.agitman.moe.dao.annotations.Entity;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamGroup;
import ro.agitman.moe.model.ExamInstance;

import java.sql.Timestamp;

/**
 * Created by edi on 7/24/16.
 */
@Entity
public class ExamInstanceDTO {

    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer status;
    @Column
    private Timestamp startdate;
    @Column
    private String examName;
    @Column
    private String groupName;

    public ExamInstanceDTO() {
    }

    public ExamInstanceDTO(ExamInstance instance, Exam exam, ExamGroup group) {
        this.id = instance.getId();
        this.name = instance.getName();
        this.status = instance.getStatus();
        this.startdate = instance.getStartdate();
        this.examName = exam.getName();
        this.groupName = group.getName();
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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
