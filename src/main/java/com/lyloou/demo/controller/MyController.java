package com.lyloou.demo.controller;

import com.lyloou.demo.param.JsonParam;
import com.lyloou.demo.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/json")
    @ResponseBody
    public ResultInfo json() {
        return new ResultInfo(true, "ok");
    }

    @PostMapping("/json/object")
    @ResponseBody
    public ResultInfo jsonObject(@RequestBody JsonParam jsonParam) {
        String msg = String.format(" name:%s \n count: %d\n note: %s",
                jsonParam.getName(),
                jsonParam.getCount(),
                jsonParam.getNote());
        return new ResultInfo(true, msg);
    }
}