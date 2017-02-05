package com.bizreport.consumer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bizreport.consumer.adapters.RiskAdapter;
import com.bizreport.consumer.database.Company;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class RiskActivity extends AppCompatActivity {
    ArrayList<String> risks;
    Company company;
    RiskAdapter riskAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Risk Factors");
        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        company = EventBus.getDefault().removeStickyEvent(Company.class);
        risks = new ArrayList<>();
    }

    public void addRisk(View v){
        new MaterialDialog.Builder(this)
                .title("Add new Risk Factor")
                .inputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .positiveText("Yes")
                .negativeText("No")
                .input("Risk Factor", "", false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        risks.add(input.toString());
                        riskAdapter = new RiskAdapter(risks);
                        recyclerView.setAdapter(riskAdapter);
                    }
                })
                .show();
    }

    public void next(View v){
        company.setRiskFactors(risks.size() > 1 ? TextUtils.join(":", risks) : risks+":");
        EventBus.getDefault().postSticky(company);
        startActivity(new Intent(this, ExpenseActivity.class));
    }

}
