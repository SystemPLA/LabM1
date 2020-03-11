package ru.systempla.lab_m_1.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.systempla.lab_m_1.App;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App app() {
        return app;
    }
}