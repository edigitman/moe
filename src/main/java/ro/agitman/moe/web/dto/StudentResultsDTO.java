package ro.agitman.moe.web.dto;

import java.math.BigDecimal;

/**
 * Created by edi on 8/17/16.
 */
public class StudentResultsDTO {

    private String name;
    private Integer points;
    private BigDecimal pointsPerc;
    private BigDecimal itemsPerc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public BigDecimal getPointsPerc() {
        return pointsPerc;
    }

    public void setPointsPerc(BigDecimal pointsPerc) {
        this.pointsPerc = pointsPerc;
    }

    public BigDecimal getItemsPerc() {
        return itemsPerc;
    }

    public void setItemsPerc(BigDecimal itemsPerc) {
        this.itemsPerc = itemsPerc;
    }
}
