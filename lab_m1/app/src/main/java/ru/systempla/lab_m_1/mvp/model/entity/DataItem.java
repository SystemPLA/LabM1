package ru.systempla.lab_m_1.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class DataItem {

    @Expose
    private Integer id;
    @Expose
    private String fsym;
    @Expose
    private String tsym;
    @Expose
    private String exchange;
    @Expose
    private Integer currentPrice;
    @Expose
    private Integer _1DABS;
    @Expose
    private Integer _7DABS;
    @Expose
    private Integer _30DABS;
    @Expose
    private Integer _1DPROC;
    @Expose
    private Integer _7DPROC;
    @Expose
    private Integer _30DPROC;

    public Integer getId() {
        return id;
    }

    public String getFsym() {
        return fsym;
    }

    public String getTsym() {
        return tsym;
    }

    public String getExchange() {
        return exchange;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public Integer get_1DABS() {
        return _1DABS;
    }

    public Integer get_7DABS() {
        return _7DABS;
    }

    public Integer get_30DABS() {
        return _30DABS;
    }

    public Integer get_1DPROC() {
        return _1DPROC;
    }

    public Integer get_7DPROC() {
        return _7DPROC;
    }

    public Integer get_30DPROC() {
        return _30DPROC;
    }
}