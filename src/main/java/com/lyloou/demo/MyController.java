package com.lyloou.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 标识为控制器
public class MyController {
    //@RequestMapping来配置HandlerMapping
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        // 使用数据模型捆绑数据
        model.addAttribute("message", "Hello World!!");
        // 返回逻辑视图名称
        return "index";
    }
}