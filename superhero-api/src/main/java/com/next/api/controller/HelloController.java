package com.next.api.controller;

import com.next.api.config.RabbitMqConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore  // 测试服务不暴露给接口文档
public class HelloController extends BasicController{

    @GetMapping("/hello")
    public Object hello() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TOPIC_PUSH, "push.order.do", "测试生产！");
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
