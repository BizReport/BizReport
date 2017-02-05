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

public class ExpenseActivity extends AppCompatActivity {

    ArrayList<String> expenses;
    Company company;
    ExpenseAdapter expenseAdapter;
    RecyclerView recyclerView;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Expenses");
        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        company = EventBus.getDefault().removeStickyEvent(Company.class);
        expenses = new ArrayList<>();
    }

    public void addExpense(View v){
        if(count != 12) {
            new MaterialDialog.Builder(this)
                    .title("Add new Risk Factor")
                    .inputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                    .positiveText("Yes")
                    .negativeText("No")
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input("Expense", "", false, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            expenses.add(input.toString());
                            expenseAdapter = new ExpenseAdapter(expenses);
                            recyclerView.setAdapter(expenseAdapter);
                            ++count;
                        }
                    })
                    .show();
        }
    }

    public void next(View v){
        company.setExpenses(TextUtils.join(":", expenses));
        EventBus.getDefault().postSticky(company);
        startActivity(new Intent(this, IncomeActivity.class));
    }

}
