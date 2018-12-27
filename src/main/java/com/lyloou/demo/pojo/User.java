package com.lyloou.demo.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user") // MyBatis别名
public class User implements Serializable {
    private static final long serialVersionUID = -2323297841948476564L;
    private Long id;
    private String userName;
    private int sex; // 枚举，将来需要MyBatis的typeHandler进行转换
    private String note;

    /**
     * setter and getters
     **/
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}