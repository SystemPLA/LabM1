package ru.systempla.lab_m_1.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.systempla.lab_m_1.mvp.model.api.IDataSource;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public IDataSource api() {
    return new Retrofit.Builder()
    .baseUrl("http://crypto-analyser.ml/")
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(IDataSource.class);
    }
}
