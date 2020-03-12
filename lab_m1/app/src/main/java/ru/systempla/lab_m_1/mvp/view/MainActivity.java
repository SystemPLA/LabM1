package ru.systempla.lab_m_1.mvp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import moxy.viewstate.MvpViewState;
import ru.systempla.lab_m_1.App;
import ru.systempla.lab_m_1.R;
import ru.systempla.lab_m_1.mvp.presenter.MainActivityPresenter;
import ru.systempla.lab_m_1.navigation.Screens;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    Router router;

    @Inject
    NavigatorHolder navigatorHolder;

    Navigator navigator = new SupportAppNavigator(this, R.id.nav_host_fragment);

    @InjectPresenter
    MainActivityPresenter presenter;

    @ProvidePresenter
    public MainActivityPresenter providePresenter() {
        MainActivityPresenter presenter = new MainActivityPresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getInstance().getAppComponent().inject(this);

        if (savedInstanceState == null) {
            router.replaceScreen(new Screens.ChartsScreen());
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_markets:
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                bottomNavigationView.setSelected(true);
                presenter.navigateToMarkets();
                break;
            case R.id.navigation_portfolio:
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
                bottomNavigationView.setSelected(true);
                presenter.navigateToPortfolio();
                break;
            case R.id.navigation_charts:
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                bottomNavigationView.setSelected(true);
                presenter.navigateToCharts();
                break;
        }
        return false;
    }
}
