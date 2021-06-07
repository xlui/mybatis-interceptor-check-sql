package com.naive.benchmark.mybatisinterceptorchecksql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.naive.benchmark.mybatisinterceptorchecksql.mapper")
public class MybatisInterceptorCheckSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisInterceptorCheckSqlApplication.class, args);
    }

}
