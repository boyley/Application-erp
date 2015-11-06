package com.github.application.erp.service.impl;

import com.github.application.erp.entity.Order;
import com.github.application.erp.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Bogle on 2015/11/3.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Page<Order> findPager(Pageable pageable, Order query) {
        return null;
    }
}
