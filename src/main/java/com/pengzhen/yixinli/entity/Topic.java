package com.pengzhen.yixinli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Topic {
    private Integer id;

    private String  topicContent;

    private String referAnswer;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date topicTime;

    private String userOp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getReferAnswer() {
        return referAnswer;
    }

    public void setReferAnswer(String referAnswer) {
        this.referAnswer = referAnswer;
    }

    public Date getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Date topicTime) {
        this.topicTime = topicTime;
    }

    public String getUserOp() {
        return userOp;
    }

    public void setUserOp(String userOp) {
        this.userOp = userOp;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicContent='" + topicContent + '\'' +
                ", referAnswer='" + referAnswer + '\'' +
                ", topicTime=" + topicTime +
                ", userOp='" + userOp + '\'' +
                '}';
    }
}