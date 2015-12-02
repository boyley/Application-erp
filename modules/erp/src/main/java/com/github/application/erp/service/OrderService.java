package com.github.application.erp.service;

import com.github.application.erp.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Bogle on 2015/11/3.
 */
public interface OrderService {

    Page<Order> findPager(Pageable pageable, Order query);

    Order save(Order order);

    Order findOne(Long id);

    int remove(List<Order> orders);

    /**
     * 查询指定条件下的所有信息
     *
     * @param query
     * @return
     */
    byte[] findAll(Order query);
}
