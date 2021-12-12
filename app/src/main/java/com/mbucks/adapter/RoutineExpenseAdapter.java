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

import com.mbucks.R;
import com.mbucks.model.MyRoutineExpenseModel;

import java.util.ArrayList;

public class RoutineExpenseAdapter extends RecyclerView.Adapter<RoutineExpenseAdapter.MyViewHolder> {
    private final Context mContext;
    private final ArrayList<MyRoutineExpenseModel> myRoutineExpenseModelArrayList;
    DeleteOnClick deleteOnClick;

    public interface DeleteOnClick {
        void onDeleteClick(int position);
    }

    EditOnClick editOnClick;

    public interface EditOnClick {
        void onEditClick(int position);
    }

    public RoutineExpenseAdapter(Context context, ArrayList<MyRoutineExpenseModel> myRoutineExpenseModelArrayList,
                                 DeleteOnClick deleteOnClick, EditOnClick editOnClick) {
        this.mContext = context;
        this.myRoutineExpenseModelArrayList = myRoutineExpenseModelArrayList;
        this.deleteOnClick = deleteOnClick;
        this.editOnClick = editOnClick;
    }

    @NonNull
    @Override
    public RoutineExpenseAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay_routine_expense_item, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RoutineExpenseAdapter.MyViewHolder myViewHolder, final int i) {
        final MyRoutineExpenseModel myRoutineExpenseModel = myRoutineExpenseModelArrayList.get(i);
        myViewHolder.tvTitle.setText(myRoutineExpenseModel.title);
        myViewHolder.tvDuration.setText(myRoutineExpenseModel.duration);
        myViewHolder.tvGoalAmt.setText(myRoutineExpenseModel.goalAmount);
        myViewHolder.tvHaveAmt.setText(myRoutineExpenseModel.haveAmount);
        myViewHolder.tvShortFallAmt.setText(myRoutineExpenseModel.shortfall);
        myViewHolder.icDelete.setOnClickListener(v -> deleteOnClick.onDeleteClick(myViewHolder.getAdapterPosition()));
        myViewHolder.icEdit.setOnClickListener(v -> editOnClick.onEditClick(myViewHolder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return myRoutineExpenseModelArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle, tvDuration, tvGoalAmt, tvHaveAmt, tvShortFallAmt;
        private LinearLayout llRequireAmount;
        private ImageView icDelete, icEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvGoalAmt = itemView.findViewById(R.id.tvGoalAmt);
            tvHaveAmt = itemView.findViewById(R.id.tvHaveAmt);
            tvShortFallAmt = itemView.findViewById(R.id.tvShortFallAmt);
            llRequireAmount = itemView.findViewById(R.id.llRequireAmount);
            icDelete = itemView.findViewById(R.id.icDelete);
            icEdit = itemView.findViewById(R.id.icEdit);
        }
    }
}

