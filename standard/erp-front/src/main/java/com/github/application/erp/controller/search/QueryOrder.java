package com.github.application.erp.controller.search;

import com.github.application.erp.entity.Order;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

/**
 * Created by Bogle on 2015/11/3.
 */
public class QueryOrder extends Order {

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Calendar startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Calendar endTime;

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}
