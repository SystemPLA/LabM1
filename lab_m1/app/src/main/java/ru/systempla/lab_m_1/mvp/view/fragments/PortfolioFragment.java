package ru.systempla.lab_m_1.mvp.view.fragments;

import android.view.View;
import android.widget.TextView;

import moxy.MvpAppCompatFragment;
import ru.systempla.lab_m_1.Constants;
import ru.systempla.lab_m_1.R;

public class PortfolioFragment extends MvpAppCompatFragment {

    public static PortfolioFragment newInstance(){
        return new PortfolioFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TextView) getActivity().findViewById(R.id.toolbar_title)).setText(Constants.PORTFOLIO);
        getActivity().findViewById(R.id.add_button).setVisibility(View.GONE);
        getActivity().findViewById(R.id.clear_button).setVisibility(View.GONE);
    }
}
