package ro.agitman.moe.model;

import java.sql.Timestamp;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class VerificationToken {

    private Integer id;
    private Timestamp expiredate;
    private String token;
    private Integer verified;
    private Integer userid;
    private Timestamp datecreated;

}
