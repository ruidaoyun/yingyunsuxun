package com.belle.yingyunsuxun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.belle.yingyunsuxun.dao")
public class YingyunsuxunApplication {

    public static void main(String[] args) {
        SpringApplication.run (YingyunsuxunApplication.class, args);
    }
}
