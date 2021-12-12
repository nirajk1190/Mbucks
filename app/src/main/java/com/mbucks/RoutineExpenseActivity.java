package com.mbucks;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mbucks.adapter.RoutineExpenseAdapter;
import com.mbucks.model.MyRoutineExpenseModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class RoutineExpenseActivity extends AppCompatActivity implements RoutineExpenseAdapter.EditOnClick, RoutineExpenseAdapter.DeleteOnClick {
    private Context mContext;
    private ArrayList<MyRoutineExpenseModel> myRoutineExpenseModelArrayList = new ArrayList<>();
    private RoutineExpenseAdapter routineExpenseAdapter;
    private RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_summary);
        mContext = RoutineExpenseActivity.this;

        initViews();
    }

    private void initViews() {
        rvList = findViewById(R.id.rvList);

        loadData();

    }

    private void loadData() {
        myRoutineExpenseModelArrayList.clear();
        try {
            String json = Utils.getAssetJsonResponse(mContext, "routine_summary.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            for (int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {
                JSONObject obj = jsonArray.optJSONObject(i);
                MyRoutineExpenseModel myRoutineExpenseModel = new MyRoutineExpenseModel();
                myRoutineExpenseModel.title = obj.optString("title");
                myRoutineExpenseModel.duration = obj.optString("duration");
                myRoutineExpenseModel.goalAmount = obj.optString("goalAmount");
                myRoutineExpenseModel.haveAmount = obj.optString("haveAmount");
                myRoutineExpenseModel.shortfall = obj.optString("shortfall");
                myRoutineExpenseModelArrayList.add(myRoutineExpenseModel);
            }
            rvList.setHasFixedSize(true);
            rvList.setLayoutManager(new LinearLayoutManager(mContext));
            routineExpenseAdapter = new RoutineExpenseAdapter(mContext, myRoutineExpenseModelArrayList, this, this);
            rvList.setAdapter(routineExpenseAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEditClick(int position) {
        MyRoutineExpenseModel myRoutineExpenseModel = myRoutineExpenseModelArrayList.get(position);

    }

    @Override
    public void onDeleteClick(int position) {
        MyRoutineExpenseModel myRoutineExpenseModel = myRoutineExpenseModelArrayList.get(position);

    }
}
