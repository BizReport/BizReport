package com.bizreport.consumer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bizreport.consumer.R;
import com.bizreport.consumer.adapters.ExpenseAdapter;
import com.bizreport.consumer.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class ExpenseFragment extends Fragment {

    public ExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expense, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        String exp = getArguments().getString("exp");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(exp.split(":")));
        recyclerView.setAdapter(new ExpenseAdapter(list));
        return view;
    }
}
