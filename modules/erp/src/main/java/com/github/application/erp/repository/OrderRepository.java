package com.github.application.erp.repository;

import com.github.application.erp.entity.Order;
import org.springframework.data.mybatis.repository.MyBatisRepository;

import java.util.List;

public interface OrderRepository extends MyBatisRepository<Order, Long> {

    int updates(List<Order> orders);
}