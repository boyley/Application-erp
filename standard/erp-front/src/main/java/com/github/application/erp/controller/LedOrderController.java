package com.github.application.erp.controller;

import com.github.application.erp.controller.search.QueryProduce;
import com.github.application.erp.entity.Product;
import com.github.application.erp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 赵波 on 2015/10/28.
 */
@RestController
@RequestMapping(value = "order")
@io.swagger.annotations.Api(value = "LED订单管理")
public class LedOrderController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/publish")
    @ApiOperation(value = "添加订单页面")
    public ModelAndView publish() {
        List<Product> products = productService.find(new QueryProduce());
        ModelAndView modelAndView = new ModelAndView("admin/order/led-order-edit");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
