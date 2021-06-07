package com.naive.benchmark.mybatisinterceptorchecksql.mapper;

import com.naive.benchmark.mybatisinterceptorchecksql.entity.Product;
import org.apache.ibatis.annotations.Select;

public interface ProductMapper {
    @Select("SELECT * FROM product WHERE product_id = #{productId}")
    Product selectByProductId(long productId);
}
