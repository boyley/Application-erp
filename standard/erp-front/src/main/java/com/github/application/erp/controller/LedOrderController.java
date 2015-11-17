package com.github.application.erp.controller;

import com.github.application.erp.controller.search.QueryOrder;
import com.github.application.erp.controller.web.Api;
import com.github.application.erp.entity.Order;
import com.github.application.erp.entity.Product;
import com.github.application.erp.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public Page<Order> list(@ApiParam(required = true, value = "分页信息") Pageable pageable, @ApiParam(required = false, value = "查询条件") QueryOrder queryOrder) {
        Page<Order> page = orderService.findPager(pageable, queryOrder);
        return page;
    }

    @ApiOperation(value = "修改LED产品")
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView edit(@ApiParam(required = true, value = "需要修改的LED订单ID号") @RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/order/led-order-edit");
        Order order = this.orderService.selectByPrimaryKey(id);
        modelAndView.addObject(order);
        return modelAndView;
    }

    @ApiOperation(value = "添加LED订单页面")
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ModelAndView publish() {
        ModelAndView modelAndView = new ModelAndView("admin/order/led-order-edit");
        return modelAndView;
    }

    @ApiOperation(value = "添加LED产品")
    @RequestMapping(value = "/publish", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public
    @ResponseBody
    Object publish(@ApiParam(required = true, value = "添加LED产品") @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return new Api<>(false, 501, result.getAllErrors());
        }
        String message = order.isNew() ? "添加成功" : "修改成功";
        order = this.orderService.save(order);
        Api<Order> api = new Api<>(!order.isNew(), message, order);
        return api;
    }

}
