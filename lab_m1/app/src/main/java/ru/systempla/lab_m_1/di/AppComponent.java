package ru.systempla.lab_m_1.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.systempla.lab_m_1.MainActivity;

@Singleton
@Component(modules = {

})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
