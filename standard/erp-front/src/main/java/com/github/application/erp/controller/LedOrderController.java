package com.github.application.erp.controller;

import com.github.application.erp.controller.search.QueryOrder;
import com.github.application.erp.entity.Order;
import com.github.application.erp.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 赵波 on 2015/10/28.
 */
@RestController
@RequestMapping(value = "order")
@io.swagger.annotations.Api(value = "LED订单管理")
public class LedOrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "分页获取LED订单列表信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView list(Pageable pageable, QueryOrder queryOrder) {
        Page<Order> page = orderService.findPager(pageable, queryOrder);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping(value = "/publish")
    @ApiOperation(value = "添加订单页面")
    public ModelAndView publish() {
        return null;
    }
}
