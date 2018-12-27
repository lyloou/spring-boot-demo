package com.lyloou.demo.dao;

import com.lyloou.demo.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {


    public int insertRole(Role role);


    public int updateRole(Role role);


    public Role getRole(Long id);


    public List<Role> findRoles(@Param("roleName") String roleName,
                                @Param("start") int start, @Param("limit") int limit);


    public Integer countRoles(@Param("roleName") String roleName);


    public Integer deleteRole(@Param("id") Long id);
}