package ru.systempla.lab_m_1;

import android.app.Application;

import ru.systempla.lab_m_1.di.AppComponent;
import ru.systempla.lab_m_1.di.DaggerAppComponent;
import ru.systempla.lab_m_1.di.modules.AppModule;

public class App extends Application {
    static private App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
