package com.pengzhen.yixinli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户留言实体类
 */
public class Leacot {
    private Integer id;

    private String content;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date leacotsTime;

    private String leacotsUser;

    private Reply replyId; //关联用户回复
    private Integer status;


    public Leacot() {
    }

    public Leacot(Integer id, String content, Date leacotsTime, String leacotsUser, Reply replyId, Integer status) {
        this.id = id;
        this.content = content;
        this.leacotsTime = leacotsTime;
        this.leacotsUser = leacotsUser;
        this.replyId = replyId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLeacotsTime() {
        return leacotsTime;
    }

    public void setLeacotsTime(Date leacotsTime) {
        this.leacotsTime = leacotsTime;
    }

    public String getLeacotsUser() {
        return leacotsUser;
    }

    public void setLeacotsUser(String leacotsUser) {
        this.leacotsUser = leacotsUser;
    }

    public Reply getReplyId() {
        return replyId;
    }

    public void setReplyId(Reply replyId) {
        this.replyId = replyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}