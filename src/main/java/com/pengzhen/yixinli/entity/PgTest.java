package com.pengzhen.yixinli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PgTest {
    private Integer id;

    private String pgtestResult;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pgtestTime;

    private int pgtestScore;

    private String userOp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPgtestResult() {
        return pgtestResult;
    }

    public void setPgtestResult(String pgtestResult) {
        this.pgtestResult = pgtestResult;
    }

    public Date getPgtestTime() {
        return pgtestTime;
    }

    public void setPgtestTime(Date pgtestTime) {
        this.pgtestTime = pgtestTime;
    }

    public String getUserOp() {
        return userOp;
    }

    public void setUserOp(String userOp) {
        this.userOp = userOp;
    }

    public int getPgtestScore() {
        return pgtestScore;
    }

    public void setPgtestScore(int pgtestScore) {
        this.pgtestScore = pgtestScore;
    }
}