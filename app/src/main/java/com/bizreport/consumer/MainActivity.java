package com.bizreport.consumer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bizreport.consumer.database.Company;
import com.bizreport.consumer.database.DatabaseHandler;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<Company> companies = DatabaseHandler.getInstance(this).getAllCompanies();
        Log.d("size","size="+DatabaseHandler.getInstance(this).dbSize());
        companies.add(new Company("New Company Consumer Report"));
        final IDrawerItem[] items = buildDrawerItems(companies);
        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(items)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(position == items.length-1){
                            new MaterialDialog.Builder(MainActivity.this)
                                    .title("New Company's Name")
                                    .positiveText("Yes")
                                    .negativeText("No")
                                    .inputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                                    .input("Name", "", false, new MaterialDialog.InputCallback() {
                                        @Override
                                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                            EventBus.getDefault().postSticky(new Company(input.toString()));
                                            startActivity(new Intent(MainActivity.this, RiskActivity.class));
                                        }
                                    })
                                    .show();
                        }
                        else{
                            EventBus.getDefault().postSticky(companies.get(position));
                            startActivity(new Intent(MainActivity.this, OverviewActivity.class));
                        }
                return true;
                    }
                })
                .build();

    }

    public IDrawerItem[] buildDrawerItems(ArrayList<Company> companies){
        ArrayList<IDrawerItem> items = new ArrayList<>();
        for(int i = 0; i < companies.size(); ++i){
            PrimaryDrawerItem item = i != companies.size()-1 ? new PrimaryDrawerItem().withName(companies.get(i).getName()) : new PrimaryDrawerItem().withName(companies.get(i).getName()).withIcon(R.drawable.plus);
            items.add(item);
        }
        return items.toArray(new IDrawerItem[items.size()]);
    }
}