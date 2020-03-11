package ru.systempla.lab_m_1.mvp.model.repo;

import io.reactivex.Single;
import ru.systempla.lab_m_1.mvp.model.api.IDataSource;
import ru.systempla.lab_m_1.mvp.model.entity.DataItem;

public class LabMRepo implements ILabMRepo {

    private IDataSource api;

    public LabMRepo(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<DataItem> loadDataItems() {
        return api.loadDataItems();
    }

}
