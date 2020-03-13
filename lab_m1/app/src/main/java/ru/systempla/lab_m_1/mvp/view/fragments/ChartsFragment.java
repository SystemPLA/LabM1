package ru.systempla.lab_m_1.mvp.view.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.lab_m_1.App;
import ru.systempla.lab_m_1.R;
import ru.systempla.lab_m_1.mvp.presenter.ChartsPresenter;
import ru.systempla.lab_m_1.mvp.view.ChartsView;
import ru.systempla.lab_m_1.mvp.view.adapter.ChartRVAdapter;
import ru.systempla.lab_m_1.mvp.view.list.RecyclerTouchListener;

public class ChartsFragment extends MvpAppCompatFragment implements ChartsView {

    public static ChartsFragment newInstance(){
        return new ChartsFragment();
    }

    private ChartRVAdapter adapter;
    private RecyclerTouchListener touchListener;
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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
        presenter.loadChartsData();
        recyclerView.addOnItemTouchListener(touchListener);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChartRVAdapter(presenter.getChartListPresenter());
        touchListener = new RecyclerTouchListener(getActivity(), recyclerView);
        touchListener
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        presenter.inflateSubMenu(position);
                    }
                })
                .setSwipeOptionViews(R.id.delete_task,R.id.edit_task)
                .setSwipeable(R.id.rowFG, R.id.rowBG, (viewID, position) -> {
                    switch (viewID){
                        case R.id.delete_task:
                            presenter.onDeleteMenuPressed(position);
                            break;
                        case R.id.edit_task:
                            presenter.onChangeMenuPressed(position);
                            break;
                    }
                });
        recyclerView.setAdapter(adapter);

        ImageButton addButton = getActivity().findViewById(R.id.add_button);
        ImageButton clearButton = getActivity().findViewById(R.id.clear_button);

        addButton.setVisibility(View.VISIBLE);
        clearButton.setVisibility(View.VISIBLE);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddPressed();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClearPressed();
            }
        });
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
        popupMenu.inflate(R.menu.chart_item_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
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
        ((TextView) getActivity().findViewById(R.id.toolbar_title)).setText(title);
    }
}
