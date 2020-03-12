package ru.systempla.lab_m_1.mvp.view.list;

public interface ChartItemView {

    int getPos();
    void setPair(String pairPartOne, String pairPartTwo);
    void setExchange (String exchange);
    void setCurPrice (float curPrice);
    void set1dChange (float _1dChange);
    void set1dPercent (float _1dPercent);
    void set7dChange (float _7dChange);
    void set7dPercent (float _7dPercent);
    void set30dChange (float _30dChange);
    void set30dPercent (float _30dPercent);
}
