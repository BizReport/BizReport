package com.bizreport.consumer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bizreport.consumer.R;
import com.bizreport.consumer.adapters.ExpenseAdapter;
import com.bizreport.consumer.database.Company;
import com.bizreport.consumer.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfitFragment extends Fragment {


    public ProfitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profit, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        String exp = getArguments().getString("exp");
        String inc = getArguments().getString("inc");
        ArrayList<String> expList = new ArrayList<>(Arrays.asList(exp.split(":")));
        ArrayList<String> incList = new ArrayList<>(Arrays.asList(inc.split(":")));
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < expList.size(); ++i) list.add(String.valueOf(Double.parseDouble(incList.get(i)) - Double.parseDouble(expList.get(i))));
        recyclerView.setAdapter(new ExpenseAdapter(list));
        return view;
    }

}
