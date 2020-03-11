package ru.systempla.lab_m_1.mvp.model.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.systempla.lab_m_1.mvp.model.entity.DataItem;

public interface IDataSource {
    @GET("?getData")
    Single<DataItem> loadDataItems();
}
