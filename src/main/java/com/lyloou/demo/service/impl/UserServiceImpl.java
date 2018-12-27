package com.lyloou.demo.service.impl;


import com.lyloou.demo.dao.UserDao;
import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.SexEnum;
import com.lyloou.demo.pojo.User;
import com.lyloou.demo.service.UserRoleService;
import com.lyloou.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    @Autowired
    private UserRoleService userRoleService = null;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public PageData<User> findUsers(String userName, SexEnum sex, int pageNum, int pageSize) {
        int start = (pageNum - 1) * pageSize;
        List<User> userList = userDao.findUsers(userName, sex, start, pageSize);
        int total = userDao.countUsers(userName, sex);
        PageData<User> userPageData = new PageData<>(total, pageSize, pageNum, userList);
        return userPageData;
    }

    @Override
    public Integer insertUser(User user, Long[] roleIds) {
        int result = userDao.insertUser(user);
        userRoleService.insertUserRoles(user.getId(), roleIds);
        return result;
    }

    @Override
    public Integer updateUser(User user, Long[] roleIds) {
        int result = userDao.updateUser(user);
        userRoleService.deleteUserRoleByUserId(user.getId());
        userRoleService.insertUserRoles(user.getId(), roleIds);
        return result;
    }

    @Override
    public User deleteUser(Long id) {
        User user = userDao.getUser(id);
        int result = userDao.deleteUser(id);
        userRoleService.deleteUserRoleByUserId(id);
        return result > 0 ? user : null;
    }
}
