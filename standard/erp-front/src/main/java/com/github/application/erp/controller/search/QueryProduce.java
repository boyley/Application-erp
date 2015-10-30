package com.github.application.erp.controller.search;


import com.github.application.erp.entity.Product;

import java.util.Calendar;

/**
 * Created by 赵波 on 2015/10/23.
 */
public class QueryProduce extends Product {

    private Calendar startTime;
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
