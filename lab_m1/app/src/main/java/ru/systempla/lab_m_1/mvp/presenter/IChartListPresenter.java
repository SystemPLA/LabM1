package ru.systempla.lab_m_1.mvp.presenter;

import ru.systempla.lab_m_1.mvp.view.list.ChartItemView;

public interface IChartListPresenter {
    void bind(ChartItemView view);
    int getCount();
}
