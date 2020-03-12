package ru.systempla.lab_m_1.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.lab_m_1.mvp.model.repo.ILabMRepo;
import ru.systempla.lab_m_1.mvp.view.ChartsView;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class ChartsPresenter extends MvpPresenter<ChartsView> {

    @Inject
    ILabMRepo labMRepo;

    @Inject
    Router router;

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;
    private ChartsListPresenter warehouseListPresenter;

    public ChartsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
        warehouseListPresenter = new ChartsListPresenter();
    }

    public void setTitle() {
        getViewState().setToolbarTitle("Charts");
    }

    public void loadChartsData() {
    }
}
