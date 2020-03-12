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
    private float currentPrice;
    @Expose
    private float _1DABS;
    @Expose
    private float _7DABS;
    @Expose
    private float _30DABS;
    @Expose
    private float _1DPROC;
    @Expose
    private float _7DPROC;
    @Expose
    private float _30DPROC;

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

    public float getCurrentPrice() {
        return currentPrice;
    }

    public float get_1DABS() {
        return _1DABS;
    }

    public float get_7DABS() {
        return _7DABS;
    }

    public float get_30DABS() {
        return _30DABS;
    }

    public float get_1DPROC() {
        return _1DPROC;
    }

    public float get_7DPROC() {
        return _7DPROC;
    }

    public float get_30DPROC() {
        return _30DPROC;
    }
}