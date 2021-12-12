package com.mbucks.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.mbucks.BaseActivity;
import com.mbucks.R;

public class DasgboardFragment extends Fragment {
    private BaseActivity activity;
    public AlertDialog reportAlertDialog;
    AlertDialog.Builder reportBuilder;
    private LinearLayout llShopping;
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    public DasgboardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DasgboardFragment newInstance(String param1, String param2) {
        DasgboardFragment fragment = new DasgboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_dashboard, container, false);

        return view;
    }

}
