package com.github.application.erp.service.impl;

import com.github.application.erp.entity.Order;
import com.github.application.erp.repository.OrderRepository;
import com.github.application.erp.service.OrderService;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bogle on 2015/11/3.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
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
        List<Order> orders = this.orderRepository.findAll(query);
        Resource res = new ClassPathResource("/xls/order-template.xls");
        try (InputStream is = res.getInputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Context context = new Context();
            context.putVar("employees", orders);
            JxlsHelper.getInstance().processTemplate(is, baos, context);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
