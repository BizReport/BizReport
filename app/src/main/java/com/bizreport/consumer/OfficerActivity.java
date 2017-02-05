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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bizreport.consumer.adapters.ExpenseAdapter;
import com.bizreport.consumer.adapters.OfficerAdapter;
import com.bizreport.consumer.adapters.RiskAdapter;
import com.bizreport.consumer.database.Company;
import com.bizreport.consumer.database.DatabaseHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class OfficerActivity extends AppCompatActivity {

    ArrayList<String> officers;
    Company company;
    OfficerAdapter officerAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Officers");
        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        company = EventBus.getDefault().removeStickyEvent(Company.class);
        officers = new ArrayList<>();
    }

    public void addOfficer(View v){
            new MaterialDialog.Builder(this)
                    .title("Add new Officer")
                    .positiveText("Yes")
                    .negativeText("No")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("Officer", "", false, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            officers.add(input.toString());
                            officerAdapter = new OfficerAdapter(officers);
                            recyclerView.setAdapter(officerAdapter);
                        }
                    })
                    .show();
    }

    public void submit(View v){
        company.setExecutiveOfficers(TextUtils.join(":", officers));
        DatabaseHandler.getInstance(this).addCompanies(company);
        Toast.makeText(this, "Submitted Report", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }

}
