package ru.systempla.lab_m_1.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.systempla.lab_m_1.di.modules.CiceroneModule;
import ru.systempla.lab_m_1.di.modules.RepoModule;
import ru.systempla.lab_m_1.mvp.presenter.ChartsPresenter;
import ru.systempla.lab_m_1.mvp.presenter.MainActivityPresenter;
import ru.systempla.lab_m_1.mvp.view.MainActivity;
import ru.systempla.lab_m_1.di.modules.AppModule;
import ru.systempla.lab_m_1.mvp.view.fragments.ChartsFragment;

@Singleton
@Component(modules = {
        RepoModule.class,
        AppModule.class,
        CiceroneModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(ChartsPresenter chartsPresenter);
    void inject(ChartsFragment chartsFragment);
}
