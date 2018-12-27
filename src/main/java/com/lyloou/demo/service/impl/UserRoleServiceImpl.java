package com.lyloou.demo.service.impl;


import com.lyloou.demo.dao.UserRoleDao;
import com.lyloou.demo.pojo.UserRole;
import com.lyloou.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao = null;

    @Override
    public List<UserRole> findUserRolesByUserId(Long userId) {
        return userRoleDao.findUserRolesByUserId(userId);
    }

    @Override
    public Integer insertUserRole(Long userId, Long roleId) {
        return userRoleDao.insertUserRole(userId, roleId);
    }

    @Override
    public Integer insertUserRoles(Long userId, Long[] roleIds) {
        int count = 0;
        for (Long roleId : roleIds) {
            count += userRoleDao.insertUserRole(userId, roleId);
        }
        return count;
    }

    @Override
    public Integer deleteUserRoleByUserId(Long userId) {
        return userRoleDao.deleteUserRoleByUserId(userId);
    }
}
