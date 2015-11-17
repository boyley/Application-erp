package com.github.application.erp.controller;


import com.github.application.erp.controller.search.QueryProduce;
import com.github.application.erp.controller.web.Api;
import com.github.application.erp.entity.Product;
import com.github.application.erp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@io.swagger.annotations.Api(value = "LED产品管理")
@RestController
@RequestMapping(value = "led")
public class LedProduceController {

    private static final Logger log = LoggerFactory.getLogger(LedProduceController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "分页获取LED列表信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Product> list(@ApiParam(required = true, value = "分页信息") Pageable pageable, @ApiParam(required = false, value = "查询条件") QueryProduce queryProduce) {
        Page<Product> page = productService.findPager(pageable, queryProduce);
        return page;
    }

    @ApiOperation(value = "添加LED产品页面")
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ModelAndView publish() {
        ModelAndView modelAndView = new ModelAndView("admin/led/led-produce-edit");
        return modelAndView;
    }

    @ApiOperation(value = "添加LED产品")
    @RequestMapping(value = "/publish", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public
    @ResponseBody
    Object publish(@ApiParam(required = true, value = "添加LED产品") @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return new Api<>(false, 501, result.getAllErrors());
        }
        String message = product.isNew() ? "添加成功" : "修改成功";
        product = this.productService.save(product);
        Api<Product> api = new Api<>(!product.isNew(), message, product);
        return api;
    }


    @ApiOperation(value = "移除产品")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    Api<List<Product>> remove(@RequestBody List<Product> products) {
        int count = this.productService.remove(products);
        Api<List<Product>> api = new Api<>(count > 0, count > 0 ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value(), products);
        return api;
    }

    @ApiOperation(value = "修改LED产品")
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView edit(@ApiParam(required = true, value = "需要修改的LED产品ID号") @RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/led/led-produce-edit");
        Product product = this.productService.selectByPrimaryKey(id);
        modelAndView.addObject(product);
        return modelAndView;
    }


}
