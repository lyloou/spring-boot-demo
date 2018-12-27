package com.lyloou.demo.controller;

import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.Role;
import com.lyloou.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService = null;


    private static final Integer DEFAULT_PAGE_NUM = 1;


    private static final Integer DEFAULT_PAGE_SIZE = 20;

    @GetMapping("/role/{id}")
    @ResponseBody
    public Role getRole(@PathVariable("id") Long id) {
        return roleService.getRole(id);
    }


    @GetMapping("/role/list")
    public String roleList() {
        return "/role/list";
    }

    @PostMapping("/roles")
    @ResponseBody
    public PageData<Role> findRoles(String roleName, @RequestParam("page") Integer pageNum,
                                    @RequestParam("rows") Integer pageSize) {
        pageNum = (pageNum == null ? DEFAULT_PAGE_NUM : pageNum);
        pageSize = (pageSize == null ? DEFAULT_PAGE_SIZE : pageSize);
        return this.roleService.findRoles(roleName, pageNum, pageSize);
    }


    @PostMapping("/role/{type}")
    @ResponseBody
    public Role saveOrUpdate(@RequestBody Role role, @PathVariable("type") String type) {
        int result = 0;
        if ("add".equals(type)) {
            result = this.roleService.insertRole(role);
        } else {
            if (0 == this.roleService.updateRole(role)) {
                throw new RuntimeException("新增或者修改角色失败了，请联系管理员。");
            } else {
                result = 1;
            }
        }
        return result > 0 ? role : null;
    }


    @DeleteMapping("/role/{id}")
    @ResponseBody
    public Role deleteRole(@PathVariable("id") Long id) {
        Role role = this.roleService.deleteRole(id);
        if (role == null) {
            throw new RuntimeException("删除角色失败了，请联系管理员。");
        }
        return role;
    }
}