package ru.systempla.lab_m_1.mvp.model.repo;


import io.reactivex.Single;
import ru.systempla.lab_m_1.mvp.model.entity.DataItem;

public interface ILabMRepo {
    Single<DataItem> loadDataItems();
}
