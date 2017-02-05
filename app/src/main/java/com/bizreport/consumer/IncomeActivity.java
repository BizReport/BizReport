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
import com.bizreport.consumer.adapters.ExpenseAdapter;
import com.bizreport.consumer.adapters.RiskAdapter;
import com.bizreport.consumer.database.Company;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class IncomeActivity extends AppCompatActivity {

    ArrayList<String> incomes;
    Company company;
    ExpenseAdapter incomeAdapter;
    RecyclerView recyclerView;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Income");
        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        company = EventBus.getDefault().removeStickyEvent(Company.class);
        incomes = new ArrayList<>();
    }

    public void addIncome(View v){
        if(count != 12) {
            new MaterialDialog.Builder(this)
                    .title("Add new Monthly Income")
                    .positiveText("Yes")
                    .negativeText("No")
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input("Income", "", false, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            incomes.add(input.toString());
                            incomeAdapter = new ExpenseAdapter(incomes);
                            recyclerView.setAdapter(incomeAdapter);
                            ++count;
                        }
                    })
                    .show();
        }
    }

    public void next(View v){
        company.setIncome(TextUtils.join(":", incomes));
        EventBus.getDefault().postSticky(company);
        startActivity(new Intent(this, OfficerActivity.class));
    }

}
