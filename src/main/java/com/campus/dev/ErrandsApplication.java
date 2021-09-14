package com.campus.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.campus.dev.dao.mapper")
public class ErrandsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrandsApplication.class, args);
    }

}
