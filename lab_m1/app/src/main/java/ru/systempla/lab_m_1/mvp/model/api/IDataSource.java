package ru.systempla.lab_m_1.mvp.model.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.systempla.lab_m_1.mvp.model.entity.DataItem;

public interface IDataSource {
    @GET("?getData")
    Single<List<DataItem>> loadDataItems();
}
