package com.lyloou.demo.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user_role")// MyBatis别名
public class UserRole implements Serializable {
    private static final long serialVersionUID = 3876141358797784545L;
    private Long userId;
    private Long roleId;
    private String userName;
    private String roleName;
    private String note;

    /**** setter and getter **/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}