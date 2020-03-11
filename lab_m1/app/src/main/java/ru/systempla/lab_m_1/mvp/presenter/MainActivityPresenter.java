package ru.systempla.lab_m_1.mvp.presenter;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.lab_m_1.mvp.view.MainView;
import ru.systempla.lab_m_1.navigation.Screens;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void navigateToMarkets() {
        router.replaceScreen(new Screens.MarketsScreen());
    }

    public void navigateToPortfolio() {
        router.replaceScreen(new Screens.PortfolioScreen());
    }

    public void navigateToCharts() {
        router.replaceScreen(new Screens.ChartsScreen());
    }
}