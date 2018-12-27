package com.lyloou.demo.dao;

import com.lyloou.demo.pojo.SexEnum;
import com.lyloou.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public User getUser(Long id);


    public List<User> findUsers(@Param("userName") String userName,
                                @Param("sex") SexEnum sex, @Param("start") int start,
                                @Param("limit") int limit);


    public int countUsers(@Param("userName") String userName, @Param("sex") SexEnum sex);


    public Integer insertUser(User user);


    public Integer updateUser(User user);


    public Integer deleteUser(@Param("id") Long id);
}