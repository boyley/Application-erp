package com.github.application.erp.controller;

import com.github.application.erp.controller.search.QueryOrder;
import com.github.application.erp.controller.search.QueryProduce;
import com.github.application.erp.controller.web.Api;
import com.github.application.erp.entity.Order;
import com.github.application.erp.entity.Product;
import com.github.application.erp.service.OrderService;
import com.github.application.erp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 赵波 on 2015/10/28.
 */
@RestController
@RequestMapping(value = "order")
@io.swagger.annotations.Api(value = "LED订单管理")
public class LedOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "分页获取LED订单列表信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Order> list(@ApiParam(required = true, value = "分页信息") Pageable pageable, @ApiParam(required = false, value = "查询条件") QueryOrder queryOrder) {
        if (queryOrder.getEndTime() != null) {
            Calendar endTimeCalendar = Calendar.getInstance();
            endTimeCalendar.setTime(queryOrder.getEndTime());
            endTimeCalendar.add(Calendar.DATE, 1);
            endTimeCalendar.setTimeInMillis(endTimeCalendar.getTimeInMillis() - 1);
            queryOrder.setEndTime(endTimeCalendar.getTime());
        }
        Page<Order> page = orderService.findPager(pageable, queryOrder);
        return page;
    }

    @ApiOperation(value = "导出模板信息")
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> export(@ApiParam(required = false, value = "查询条件") QueryOrder queryOrder) throws UnsupportedEncodingException {
        if (queryOrder.getEndTime() != null) {
            Calendar endTimeCalendar = Calendar.getInstance();
            endTimeCalendar.setTime(queryOrder.getEndTime());
            endTimeCalendar.add(Calendar.DATE, 1);
            endTimeCalendar.setTimeInMillis(endTimeCalendar.getTimeInMillis() - 1);
            queryOrder.setEndTime(endTimeCalendar.getTime());
        }

        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("LED 订单列表信息.xlsx".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] bytes = orderService.findAll(queryOrder);
        return new ResponseEntity<>(bytes,
                headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改LED产品")
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView edit(@ApiParam(required = true, value = "需要修改的LED订单ID号") @RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/order/led-order-edit");
        Order order = this.orderService.findOne(id);
        modelAndView.addObject(order);
        List<Product> products = productService.find(new QueryProduce());
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @ApiOperation(value = "添加LED订单页面")
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ModelAndView publish() {
        List<Product> products = productService.find(new QueryProduce());
        ModelAndView modelAndView = new ModelAndView("/admin/order/led-order-edit");
        modelAndView.addObject("products", products);
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


    @ApiOperation(value = "移除产品")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    Api<List<Order>> remove(@RequestBody List<Order> orders) {
        int count = this.orderService.remove(orders);
        Api<List<Order>> api = new Api<>(count > 0, count > 0 ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value(), orders);
        return api;
    }
}
