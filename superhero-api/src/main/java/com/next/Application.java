package com.next;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

// 引入了 idworker 在扫描时需要添加上路径
@SpringBootApplication(scanBasePackages = {"com.next", "org.n3r.idworker"})
@MapperScan(basePackages = "com.next.mapper")
@EnableScheduling   // 开启对于定时任务的支持
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
