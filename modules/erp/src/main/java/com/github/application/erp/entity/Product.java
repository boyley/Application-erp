package com.github.application.erp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Persistable;

import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

public class Product implements Persistable<Long> {
    private Long id;

    @Pattern(regexp = ".{2,50}", message = "生产商字符的长度在2~50之间")
    private String producer;

    @Pattern(regexp = ".{2,30}", message = "联系人字符的长度在2~50之间")
    private String contact;

    private String phone;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    private Timestamp updateTime;

    @Pattern(regexp = ".{2,30}", message = "LED型号字符的长度在2~30之间")
    private String typ;

    @Pattern(regexp = ".{2,30}", message = "LED色温字符的长度在2~30之间")
    private String colourTemperature;

    @Pattern(regexp = ".{2,30}", message = "LED亮度字符的长度在2~30之间")
    private String bri;

    private Double price;

    private String note;

    private Boolean remove = Boolean.FALSE;

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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
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
        this.email = email;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ == null ? null : typ.trim();
    }

    public String getColourTemperature() {
        return colourTemperature;
    }

    public void setColourTemperature(String colourTemperature) {
        this.colourTemperature = colourTemperature == null ? null : colourTemperature.trim();
    }

    public String getBri() {
        return bri;
    }

    public void setBri(String bri) {
        this.bri = bri == null ? null : bri.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }
}