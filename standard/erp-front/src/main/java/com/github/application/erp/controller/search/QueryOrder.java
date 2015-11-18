package com.github.application.erp.controller.search;

import com.github.application.erp.entity.Order;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Bogle on 2015/11/3.
 */
public class QueryOrder extends Order {

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
