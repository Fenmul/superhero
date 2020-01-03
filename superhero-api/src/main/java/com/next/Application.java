package com.next;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

// 引入了 idworker 在扫描时需要添加上路径
@SpringBootApplication(scanBasePackages = {"com.next", "org.n3r.idworker"})
@MapperScan(basePackages = "com.next.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
