package ru.systempla.lab_m_1.navigation;

import androidx.fragment.app.Fragment;

import ru.systempla.lab_m_1.mvp.view.fragments.ChartsFragment;
import ru.systempla.lab_m_1.mvp.view.fragments.MarketsFragment;
import ru.systempla.lab_m_1.mvp.view.fragments.PortfolioFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class ChartsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return ChartsFragment.newInstance();
        }
    }

    public static class PortfolioScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return PortfolioFragment.newInstance();
        }
    }

    public static class MarketsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return MarketsFragment.newInstance();
        }
    }

}
