package com.lyloou.demo.controller;

import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.SexEnum;
import com.lyloou.demo.pojo.User;
import com.lyloou.demo.service.UserService;
import com.lyloou.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService = null;

    // 默认页码
    private static final Integer DEFAULT_PAGE_NUM = 1;

    // 默认页大小
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    @GetMapping("/user/list")
    public String userList() {
        return "/user/list";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserVo getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return UserVo.of(user);
    }

    @PostMapping("/user/roles/{pageNum}/{pageSize}")
    @ResponseBody
    public PageData<UserVo> findUsers(@RequestBody UserQueryParams params,
                                      @PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize) {
        pageNum = (pageNum == null ? DEFAULT_PAGE_NUM : pageNum);
        pageSize = (pageSize == null ? DEFAULT_PAGE_NUM : pageSize);
        SexEnum sex = null;
        if (params.getSexCode() != null) {
            sex = SexEnum.getSexByCode(params.getSexCode());
        }
        PageData<User> pageUser = userService.findUsers(params.getUserName(), sex, pageSize, pageSize);
        return UserVo.of(pageUser);
    }

    class UserQueryParams {
        private String userName;
        private Integer sexCode;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getSexCode() {
            return sexCode;
        }

        public void setSexCode(Integer sexCode) {
            this.sexCode = sexCode;
        }

    }
}
