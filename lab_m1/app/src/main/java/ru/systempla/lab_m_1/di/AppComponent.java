package ru.systempla.lab_m_1.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.systempla.lab_m_1.MainActivity;
import ru.systempla.lab_m_1.di.modules.AppModule;

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
