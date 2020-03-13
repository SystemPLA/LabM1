package ru.systempla.lab_m_1.mvp.view.fragments;

import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;

public class MarketsFragment extends MvpAppCompatFragment {

    public static MarketsFragment newInstance(){
        return new MarketsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle("Market Data");
    }
}
