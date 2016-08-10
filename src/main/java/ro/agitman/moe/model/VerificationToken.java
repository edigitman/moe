package ro.agitman.moe.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class VerificationToken {

    private Integer id;
    private Date expiredate;
    private String token;
    private Integer verified;
    private Integer userid;
    private Date datecreated;

    public VerificationToken(Integer id) {
        this.id = id;
    }

    public VerificationToken() {
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", verified=" + verified +
                ", userid=" + userid +
                ", datecreated=" + datecreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationToken that = (VerificationToken) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (verified != null ? !verified.equals(that.verified) : that.verified != null) return false;
        return userid != null ? userid.equals(that.userid) : that.userid == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (verified != null ? verified.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
}

