package com.mbucks.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mbucks.AddGoalAmountActivity;
import com.mbucks.AddNewGoalActivity;
import com.mbucks.BaseActivity;
import com.mbucks.R;
import com.mbucks.RoutineExpenseActivity;
import com.mbucks.Utils;
import com.mbucks.adapter.GoalAdapter;
import com.mbucks.model.GoalSummaryModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class GoalFragment extends Fragment implements View.OnClickListener, GoalAdapter.CardOnClick, GoalAdapter.AmountOnClick {
    private BaseActivity activity;
    private Context mContext;
    private View view;
    private LinearLayout llInvestment, llIncome, llGoal;
    private TextView tvInvestment, tvIncome, tvGoal;
    private ArrayList<GoalSummaryModel> goalSummaryModelArrayList = new ArrayList<>();
    private RecyclerView rvGoals;
    private GoalAdapter goalAdapter;
    private int[] icons = new int[]{R.drawable.life_insurance, R.drawable.ic_retirement, R.drawable.marriage, R.drawable.education,
            R.drawable.home, R.drawable.anniversary, R.drawable.vehicle, R.drawable.contingency, R.drawable.routine};

    private LinearLayout llAddGoal;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    public GoalFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GoalFragment newInstance(String param1, String param2) {
        GoalFragment fragment = new GoalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_goal, container, false);
        mContext = getActivity();
        initViews();
        return view;
    }

    private void initViews() {
        llInvestment = view.findViewById(R.id.llInvestment);
        llIncome = view.findViewById(R.id.llIncome);
        llGoal = view.findViewById(R.id.llGoal);

        tvInvestment = view.findViewById(R.id.tvInvestment);
        tvIncome = view.findViewById(R.id.tvIncome);
        tvGoal = view.findViewById(R.id.tvGoal);

        rvGoals = view.findViewById(R.id.rvGoals);
        llAddGoal = view.findViewById(R.id.llAddGoal);


        llGoal.setOnClickListener(this);
        llIncome.setOnClickListener(this);
        llInvestment.setOnClickListener(this);
        llAddGoal.setOnClickListener(this);

        loadGoalData();

    }

    private void loadGoalData() {
        goalSummaryModelArrayList.clear();
        try {
            String json = Utils.getAssetJsonResponse(mContext, "goal_summary.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            for (int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {
                JSONObject obj = jsonArray.optJSONObject(i);
                GoalSummaryModel goalSummaryModel = new GoalSummaryModel();
                goalSummaryModel.title = obj.optString("title");
                goalSummaryModel.current_age = obj.optString("current_age");
                goalSummaryModel.age_at = obj.optString("age_at");
                goalSummaryModel.required_amt = obj.optString("required_amt");
                goalSummaryModel.have_amt = obj.optString("have_amt");
                goalSummaryModel.shortfall_amt = obj.optString("shortfall_amt");
                goalSummaryModel.icon = icons[i];
                goalSummaryModelArrayList.add(goalSummaryModel);
            }
            rvGoals.setHasFixedSize(true);
            rvGoals.setLayoutManager(new LinearLayoutManager(mContext));
            goalAdapter = new GoalAdapter(mContext, goalSummaryModelArrayList, this,this);
            rvGoals.setAdapter(goalAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llGoal) {
            llGoal.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_active));
            tvGoal.setTextColor(ContextCompat.getColor(mContext, R.color.white));

            llIncome.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_inactive));
            tvIncome.setTextColor(ContextCompat.getColor(mContext, R.color.tab_txt));

            llInvestment.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_inactive));
            tvInvestment.setTextColor(ContextCompat.getColor(mContext, R.color.tab_txt));

        }
        if (v.getId() == R.id.llIncome) {
            llIncome.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_active));
            tvIncome.setTextColor(ContextCompat.getColor(mContext, R.color.white));

            llGoal.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_inactive));
            tvGoal.setTextColor(ContextCompat.getColor(mContext, R.color.tab_txt));

            llInvestment.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_inactive));
            tvInvestment.setTextColor(ContextCompat.getColor(mContext, R.color.tab_txt));

        }
        if (v.getId() == R.id.llInvestment) {
            llInvestment.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_active));
            tvInvestment.setTextColor(ContextCompat.getColor(mContext, R.color.white));

            llGoal.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_inactive));
            tvGoal.setTextColor(ContextCompat.getColor(mContext, R.color.tab_txt));

            llIncome.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round_btn_inactive));
            tvIncome.setTextColor(ContextCompat.getColor(mContext, R.color.tab_txt));
        }
        if (v.getId() == R.id.llAddGoal) {
            startActivity(new Intent(mContext, AddNewGoalActivity.class));
        }
    }

    @Override
    public void onCardClick(int position) {
        GoalSummaryModel goalSummaryModel = goalSummaryModelArrayList.get(position);
        if (goalSummaryModel.title.equalsIgnoreCase("My Routine Expenses")){
            startActivity(new Intent(mContext, RoutineExpenseActivity.class));
        }
    }

    @Override
    public void onAmountClick(int position) {
        startActivity(new Intent(mContext, AddGoalAmountActivity.class));
    }
}
