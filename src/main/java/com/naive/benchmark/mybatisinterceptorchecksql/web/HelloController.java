package com.naive.benchmark.mybatisinterceptorchecksql.web;

import com.naive.benchmark.mybatisinterceptorchecksql.mapper.ProductMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {
    @Resource
    private ProductMapper productMapper;

    @RequestMapping("/hello")
    public String hello() {
        return productMapper.selectByProductId(2).toString();
    }
}
