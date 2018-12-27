package com.lyloou.demo.service;


import com.lyloou.demo.pojo.UserRole;

import java.util.List;

public interface UserRoleService {

    public List<UserRole> findUserRolesByUserId(Long userId);

    public Integer insertUserRole(Long userId, Long roleId);

    public Integer insertUserRoles(Long userId, Long[] roleIds);

    public Integer deleteUserRoleByUserId(Long userId);
}
