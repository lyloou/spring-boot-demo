package com.lyloou.demo.service;


import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.SexEnum;
import com.lyloou.demo.pojo.User;

public interface UserService {

    public User getUser(Long id);

    public PageData<User> findUsers(String userName, SexEnum sex, int pageNum, int pageSize);

    public Integer insertUser(User user, Long[] roleIds);

    public Integer updateUser(User user, Long[] roleIds);

    public User deleteUser(Long id);
}
