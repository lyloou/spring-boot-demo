package com.lyloou.demo.service;

import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.Role;

public interface RoleService {

    public Role getRole(Long id);

    public PageData<Role> findRoles(String roleName, int pageNum, int pageSize);

    public int insertRole(Role role);

    public int updateRole(Role role);

    public Role deleteRole(Long id);
}