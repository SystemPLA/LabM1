package ru.systempla.lab_m_1.mvp.view.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.systempla.lab_m_1.R;
import ru.systempla.lab_m_1.mvp.presenter.IChartListPresenter;
import ru.systempla.lab_m_1.mvp.view.list.ChartItemView;

public class ChartRVAdapter extends RecyclerView.Adapter<ChartRVAdapter.ViewHolder> {

    private IChartListPresenter presenter;

    public ChartRVAdapter(IChartListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bind(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ChartItemView {

        int pos = 0;

        @BindString(R.string.percent)
        String percent;

        @BindColor(R.color.colorNegativeValue)
        int negativeColor;

        @BindColor(R.color.colorPositiveValue)
        int positiveColor;

        @BindView(R.id.item_pair_value)
        TextView pairValue;

        @BindView(R.id.item_exchange_value)
        TextView exchangeValue;

        @BindView(R.id.item_cur_price_value)
        TextView curPriceValue;

        @BindView(R.id.item_1d_value)
        TextView _1dValue;

        @BindView(R.id.item_1d_percent)
        TextView _1dPercent;

        @BindView(R.id.item_7d_value)
        TextView _7dValue;

        @BindView(R.id.item_7d_percent)
        TextView _7dPercent;

        @BindView(R.id.item_30d_value)
        TextView _30dValue;

        @BindView(R.id.item_30d_percent)
        TextView _30dPercent;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setPair(String pairPartOne, String pairPartTwo) {
            pairValue.setText(String.format("%s %s", pairPartOne, pairPartTwo));
        }

        @Override
        public void setExchange(String data) {
            exchangeValue.setText(data);
        }

        @Override
        public void setCurPrice(float data) {
            curPriceValue.setText(String.valueOf(data));
        }

        private void setColor(float data, TextView target) {
            if (data<0) {
                target.setTextColor(negativeColor);
            }
            if (data>0) {
                target.setTextColor(positiveColor);
            }
        }

        @Override
        public void set1dChange(float data) {
            setColor(data, _1dValue);
            _1dValue.setText(String.valueOf(data));
        }

        @Override
        public void set1dPercent(float data) {
            setColor(data, _1dPercent);
            _1dPercent.setText(String.format("%s%s", data, percent));
        }

        @Override
        public void set7dChange(float data) {
            setColor(data, _7dValue);
            _7dValue.setText(String.valueOf(data));
        }

        @Override
        public void set7dPercent(float data) {
            setColor(data, _7dPercent);
            _7dPercent.setText(String.format("%s%s", data, percent));
        }

        @Override
        public void set30dChange(float data) {
            setColor(data, _30dValue);
            _30dValue.setText(String.valueOf(data));
        }

        @Override
        public void set30dPercent(float data) {
            setColor(data, _30dPercent);
            _30dPercent.setText(String.format("%s%s", data, percent));
        }
    }
}


