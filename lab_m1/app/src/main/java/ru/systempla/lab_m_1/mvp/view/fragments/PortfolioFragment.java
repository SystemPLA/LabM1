package ru.systempla.lab_m_1.mvp.view.fragments;

import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;

public class PortfolioFragment extends MvpAppCompatFragment {

    public static PortfolioFragment newInstance(){
        return new PortfolioFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle("Portfolio");
    }
}
