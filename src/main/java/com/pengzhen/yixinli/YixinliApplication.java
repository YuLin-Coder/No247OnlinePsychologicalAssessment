package com.pengzhen.yixinli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.pengzhen.yixinli.mapper")
@SpringBootApplication
public class YixinliApplication {

    public static void main(String[] args) {
        SpringApplication.run(YixinliApplication.class, args);
        //主方法
    }

}
