package com.next.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore  // 测试服务不暴露给接口文档
public class HelloController extends BasicController{

    @GetMapping("/hello")
    public Object hello() {
        return "hello";
    }

    @GetMapping("/set")
    public Object set() {
        redis.set("redis","helloworld");
        return "设置成功";
    }

    @GetMapping("/get")
    public Object get() {
        return redis.get("redis");
    }
}
