package ru.systempla.lab_m_1.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.systempla.lab_m_1.mvp.model.api.IDataSource;
import ru.systempla.lab_m_1.mvp.model.repo.ILabMRepo;
import ru.systempla.lab_m_1.mvp.model.repo.LabMRepo;

@Module(includes = {ApiModule.class})
public class RepoModule {

    @Singleton
    @Provides
    public ILabMRepo labMRepo(IDataSource api) {
        return new LabMRepo(api);
    }
}
