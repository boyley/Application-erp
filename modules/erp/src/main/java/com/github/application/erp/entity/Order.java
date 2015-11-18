package com.github.application.erp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Persistable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

public class Order implements Persistable<Long> {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Timestamp createTime;

    @Pattern(regexp = ".{6,50}", message = "单号字符的长度在6~30之间")
    private String orderNumber;

    @Pattern(regexp = ".{2,50}", message = "客户名字符的长度在2~30之间")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Timestamp orderTime;

    private String phone;


    private String email;

    private Long produceId;

    private Product product;

    @Min(value = 0, message = "请输入正确的价格")
    private Double price;

    @Min(value = 0, message = "请输入正确的出售价格")
    private Double sale;

    @Min(value = 1, message = "请输入正确的数量")
    private Integer number;

    private String orderAccount;

    private String orderUser;

    private Boolean remove = Boolean.FALSE;

    private String note;

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getProduceId() {
        return produceId;
    }

    public void setProduceId(Long produceId) {
        this.produceId = produceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount == null ? null : orderAccount.trim();
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser == null ? null : orderUser.trim();
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}