package ru.systempla.lab_m_1.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.lab_m_1.Constants;
import ru.systempla.lab_m_1.mvp.model.entity.DataItem;
import ru.systempla.lab_m_1.mvp.model.repo.ILabMRepo;
import ru.systempla.lab_m_1.mvp.view.ChartsView;
import ru.systempla.lab_m_1.mvp.view.list.ChartItemView;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class ChartsPresenter extends MvpPresenter<ChartsView> {

    @Inject
    ILabMRepo labMRepo;

    @Inject
    Router router;

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;
    private ChartsListPresenter chartListPresenter;

    class ChartsListPresenter implements IChartListPresenter {

        PublishSubject<ChartItemView> clickSubject = PublishSubject.create();

        List<DataItem> chartBlocks = new ArrayList<>();

        @Override
        public void bind(ChartItemView view) {
            view.setPair(chartBlocks.get(view.getPos()).getFsym(),chartBlocks.get(view.getPos()).getTsym());
            view.setExchange(chartBlocks.get(view.getPos()).getExchange());
            view.setCurPrice(chartBlocks.get(view.getPos()).getCurrentPrice());
            view.set1dChange(chartBlocks.get(view.getPos()).get_1DABS());
            view.set1dPercent(chartBlocks.get(view.getPos()).get_1DPROC());
            view.set7dChange(chartBlocks.get(view.getPos()).get_7DABS());
            view.set7dPercent(chartBlocks.get(view.getPos()).get_7DPROC());
            view.set30dChange(chartBlocks.get(view.getPos()).get_30DABS());
            view.set30dPercent(chartBlocks.get(view.getPos()).get_30DPROC());
        }

        @Override
        public int getCount() {
            return chartBlocks.size();
        }

    }
    public ChartsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
        chartListPresenter = new ChartsListPresenter();
    }
    public IChartListPresenter getChartListPresenter() {
        return chartListPresenter;
    }
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void inflateSubMenu(int position) {
        getViewState().inflateSubmenu(position);
    }

    public void setTitle() {
        getViewState().setToolbarTitle(Constants.CHARTS);
    }

    public void loadChartsData() {
        getViewState().showLoading();
        Disposable disposable = labMRepo.loadDataItems()
                .subscribeOn(ioThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(model -> {
                    chartListPresenter.chartBlocks.clear();
                    chartListPresenter.chartBlocks.addAll(model);
                    getViewState().updateList();
                    getViewState().hideLoading();
                }, t -> {
                    getViewState().showMessage(Constants.LOADING_ERROR);
                    getViewState().hideLoading();
                });
    }

    public void onChangeMenuPressed(int position) {
        getViewState().showMessage(Constants.PRESSED_EDIT);
    }

    public void onDeleteMenuPressed(int position) {
        getViewState().showMessage(Constants.PRESSED_DELETE);
    }

    public void onAddPressed() {
        getViewState().showMessage(Constants.PRESSED_ADD);
    }

    public void onClearPressed() {
        getViewState().showMessage(Constants.PRESSED_CLEAR);
    }
}
