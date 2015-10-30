package com.github.application.erp.service;

import com.github.application.erp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lenovo on 2015/10/4.
 */
public interface ProductService {


    Page<Product> findPager(Pageable pageable, Product query);

    /**
     * 保存
     *
     * @param product
     * @return
     */
    Product save(Product product);

    /**
     * 带移除的对象的id
     *
     * @param products
     * @return
     */
    int remove(final List<Product> products);

    /**
     * 根据id查询对象
     *
     * @param id
     * @return
     */
    Product selectByPrimaryKey(Long id);

    /**
     * 获取所有的数据
     *
     * @return
     */
    List<Product> find(Product product);
}
