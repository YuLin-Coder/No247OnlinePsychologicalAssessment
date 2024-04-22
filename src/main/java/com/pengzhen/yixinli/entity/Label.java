package com.pengzhen.yixinli.entity;

import java.io.Serializable;

/**
 * 文章标签实体类
 */
public class Label implements Serializable {
    private Integer id;

    private String labelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", labelName='" + labelName + '\'' +
                '}';
    }
}