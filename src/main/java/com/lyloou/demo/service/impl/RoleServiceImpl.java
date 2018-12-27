package com.lyloou.demo.service.impl;

import com.lyloou.demo.dao.RoleDao;
import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.Role;
import com.lyloou.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao = null;

    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Override
    public PageData<Role> findRoles(String roleName, int pageNum, int pageSize) {

        int start = (pageNum - 1) * pageSize;

        List<Role> roleList = roleDao.findRoles(roleName, start, pageSize);

        int total = roleDao.countRoles(roleName);

        PageData<Role> pageRoles = new PageData<>(total, pageSize, pageNum, roleList);
        return pageRoles;
    }

    @Override
    public int insertRole(Role role) {
        int result = roleDao.insertRole(role);
        return result;
    }

    @Override
    public int updateRole(Role role) {
        return this.roleDao.updateRole(role);
    }

    @Override
    public Role deleteRole(Long id) {
        Role role = this.roleDao.getRole(id);
        int result = this.roleDao.deleteRole(id);
        return result > 0 ? role : null;
    }
}