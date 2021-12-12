package com.mbucks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.mbucks.R;
import com.mbucks.model.GoalSummaryModel;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {
    private final Context mContext;
    private final ArrayList<GoalSummaryModel> goalSummaryModelArrayList;
    CardOnClick cardOnClick;
    public interface CardOnClick {
        void onCardClick(int position);
    }
    AmountOnClick amountOnClick;
    public interface AmountOnClick {
        void onAmountClick(int position);
    }

    public GoalAdapter(Context context, ArrayList<GoalSummaryModel> goalSummaryModelArrayList, CardOnClick cardOnClick,AmountOnClick amountOnClick) {
        this.mContext = context;
        this.goalSummaryModelArrayList = goalSummaryModelArrayList;
        this.cardOnClick = cardOnClick;
        this.amountOnClick = amountOnClick;
    }

    @NonNull
    @Override
    public GoalAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_goal, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GoalAdapter.MyViewHolder myViewHolder, final int i) {
        final GoalSummaryModel goalSummaryModel = goalSummaryModelArrayList.get(i);
        myViewHolder.tvTitle.setText(goalSummaryModel.title);
        myViewHolder.tvAge.setText(goalSummaryModel.current_age);
        myViewHolder.tvYears.setText(goalSummaryModel.age_at);
        myViewHolder.tvRequiredAmt.setText(goalSummaryModel.required_amt);
        myViewHolder.tvHaveAmt.setText(goalSummaryModel.have_amt);
        myViewHolder.tvShortFallAmt.setText(goalSummaryModel.shortfall_amt);
        myViewHolder.ivIcon.setImageResource(goalSummaryModel.icon);
        myViewHolder.cvMain.setOnClickListener(v -> cardOnClick.onCardClick(myViewHolder.getAdapterPosition()));
        myViewHolder.llRequireAmount.setOnClickListener(v -> amountOnClick.onAmountClick(myViewHolder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return goalSummaryModelArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle, tvAge, tvYears, tvRequiredAmt, tvHaveAmt, tvShortFallAmt;
        private final ImageView ivIcon;
        private LinearLayout llRequireAmount;
        private MaterialCardView cvMain;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvYears = itemView.findViewById(R.id.tvYears);
            tvRequiredAmt = itemView.findViewById(R.id.tvRequiredAmt);
            tvHaveAmt = itemView.findViewById(R.id.tvHaveAmt);
            tvShortFallAmt = itemView.findViewById(R.id.tvShortFallAmt);
            llRequireAmount = itemView.findViewById(R.id.llRequireAmount);
            cvMain = itemView.findViewById(R.id.cvMain);
        }
    }
}

