package com.github.application.erp.repository;

import com.github.application.erp.entity.Product;
import org.springframework.data.mybatis.repository.MyBatisRepository;

import java.util.List;

public interface ProductRepository extends MyBatisRepository<Product, Long> {

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeysSelective(List<Product> products);

    /**
     * 查询制定条件的所有数据
     * @param query
     * @return
     */
    List<Product> find(Product query);
}