package com.github.application.erp.service.impl;


import com.github.application.erp.entity.Product;
import com.github.application.erp.repository.ProductRepository;
import com.github.application.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2015/10/11.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findPager(Pageable pageable, Product query) {
        return productRepository.findAll(pageable, query);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public int remove(List<Product> products) {
        if (products.size() <= 0) {
            return 0;
        }
        return productRepository.updateByPrimaryKeysSelective(products);
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return this.productRepository.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> find(Product query) {
        return this.productRepository.find(query);
    }
}
