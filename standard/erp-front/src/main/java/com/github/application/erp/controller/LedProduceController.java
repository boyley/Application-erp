package com.github.application.erp.controller;


import com.github.application.erp.controller.search.QueryProduce;
import com.github.application.erp.controller.web.Api;
import com.github.application.erp.entity.Product;
import com.github.application.erp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lenovo on 2015/10/3.
 */
@RestController
@RequestMapping(value = "led")
public class LedProduceController {

    private static final Logger log = LoggerFactory.getLogger(LedProduceController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Product> list(Pageable pageable, QueryProduce queryProduce) {
        Page<Product> page = productService.findPager(pageable, queryProduce);
        return page;
    }

    @RequestMapping(value = "/publish", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public
    @ResponseBody
    Object publish(@Valid Product product, BindingResult result) {
        log.info("save.................");
        if (result.hasErrors()) {
            return new Api<>(false, 501, result.getAllErrors());
        }
        String message = product.isNew() ? "添加成功" : "修改成功";
        product = this.productService.save(product);
        Api<Product> api = new Api<>(!product.isNew(), message, product);
        return api;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public
    @ResponseBody
    Api<List<Product>> remove(@RequestBody List<Product> products) {
        log.info("remove.................");
        int count = this.productService.remove(products);
        Api<List<Product>> api = new Api<>(count > 0, count > 0 ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value(), products);
        return api;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/led/led-produce-edit");
        Product product = this.productService.selectByPrimaryKey(id);
        modelAndView.addObject(product);
        return modelAndView;
    }


    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ModelAndView publish() {
        ModelAndView modelAndView = new ModelAndView("admin/led/led-produce-edit");
        return modelAndView;
    }
}
