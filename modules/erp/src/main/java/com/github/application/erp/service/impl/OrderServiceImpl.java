package com.github.application.erp.service.impl;

import com.github.application.erp.entity.Order;
import com.github.application.erp.repository.OrderRepository;
import com.github.application.erp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bogle on 2015/11/3.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> findPager(Pageable pageable, Order query) {
        return orderRepository.findAll(pageable, query);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOne(Long id) {
        return this.orderRepository.findOne(id);
    }

    @Override
    public int remove(List<Order> orders) {
        if (orders.size() <= 0) {
            return 0;
        }
        return orderRepository.updates(orders);
    }

    @Override
    public byte[] findAll(Order query) {
        List<Order> iterable = this.orderRepository.findAll(query);

        return null;
    }
}
