package ru.systempla.lab_m_1.mvp.view.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.lab_m_1.App;
import ru.systempla.lab_m_1.R;
import ru.systempla.lab_m_1.mvp.presenter.ChartsPresenter;

public class ChartsFragment extends MvpAppCompatFragment {

    public static ChartsFragment newInstance(){
        return new ChartsFragment();
    }

    private ChartsRVAdapter adapter;
    private Unbinder unbinder;

    @InjectPresenter
    ChartsPresenter presenter;

    @BindView(R.id.warehouse_rv)
    RecyclerView recyclerView;

    @BindView(R.id.rl_loading)
    RelativeLayout loadingRelativeLayout;

    @ProvidePresenter
    public ChartsPresenter providePresenter(){
        ChartsPresenter presenter = new ChartsPresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.charts_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        setHasOptionsMenu(false);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
        presenter.loadChartsData();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.warehouse_creation_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.create_item) {
            presenter.onCreateMenuPressed();
            return true;
        }
        return false;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChartsRVAdapter(presenter.getChartsListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void inflateSubmenu(int position) {
        PopupMenu popupMenu = new PopupMenu(this.getContext(), recyclerView.getLayoutManager().findViewByPosition(position),
                Gravity.RELATIVE_LAYOUT_DIRECTION);
        popupMenu.inflate(R.menu.warehouse_item_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.show_item:
                    presenter.onShowMenuPressed(position);
                    return true;
                case R.id.change_item:
                    presenter.onChangeMenuPressed(position);
                    return true;
                case R.id.delete_item:
                    presenter.onDeleteMenuPressed(position);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }
}
