package ru.systempla.lab_m_1.mvp.view.fragments;

import android.view.View;
import android.widget.TextView;

import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import ru.systempla.lab_m_1.R;

public class MarketsFragment extends MvpAppCompatFragment {

    public static MarketsFragment newInstance(){
        return new MarketsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TextView) getActivity().findViewById(R.id.toolbar_title)).setText("Market Data");
        getActivity().findViewById(R.id.add_button).setVisibility(View.GONE);
        getActivity().findViewById(R.id.clear_button).setVisibility(View.GONE);
    }
}
