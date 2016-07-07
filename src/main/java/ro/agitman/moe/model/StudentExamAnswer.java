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
    private Timestamp datecreated;
}
