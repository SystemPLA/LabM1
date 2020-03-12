package ru.systempla.lab_m_1.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ChartsView extends MvpView {

    void init();
    void showLoading();
    void hideLoading();
    void updateList();
    void setToolbarTitle(String string);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showMessage(String text);
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void inflateSubmenu(int position);
}
