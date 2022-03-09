package com.netdisk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.netdisk.mapper")
public class AppStarter {

    public static void main(String[] args)
    {
        System.out.println("开始。。。。");
        SpringApplication.run(AppStarter.class);
        System.out.println("结束。。。。");
    }

}
