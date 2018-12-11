package com.zzy.shopp.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.zzy.shopp.app.biz.mapper.UserMapper"})
public class ShoppApp {
    public static void main(String[] args) {
        SpringApplication.run(ShoppApp.class, args);
    }
}
