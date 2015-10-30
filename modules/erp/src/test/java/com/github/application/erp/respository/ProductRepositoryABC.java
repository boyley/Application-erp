package com.github.application.erp.respository;

import com.alibaba.fastjson.JSON;
import com.github.application.erp.entity.Product;
import com.github.application.erp.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 赵波 on 2015/10/26.
 */
//@SpringApplicationConfiguration(classes = SampleApplication.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class ProductRepositoryABC {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryABC.class);

    @Autowired
    private ProductRepository productRepository;

//    @Test
    public void testFindPage() {
        Product product = new Product();
//        product.setProducer("producer-2");
        product.setContact("a");
        Page<Product> page = productRepository.findAll(new PageRequest(0,20),product);
        logger.info(JSON.toJSONString(page));
    }
}
