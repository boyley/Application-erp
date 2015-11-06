package com.github.application.erp.service;

import com.github.application.erp.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Bogle on 2015/11/3.
 */
public interface OrderService {

    Page<Order> findPager(Pageable pageable, Order query);
}
