package com.bizreport.consumer.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bizreport.consumer.database.Company;
import com.bizreport.consumer.fragments.ExpenseFragment;
import com.bizreport.consumer.fragments.IncomeFragment;
import com.bizreport.consumer.fragments.OfficerFragment;
import com.bizreport.consumer.fragments.ProfitFragment;
import com.bizreport.consumer.fragments.RiskFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    String[] names = {"Risks", "Expenses", "Income", "Profit", "Officers"};
    Company name;
    public ViewPagerAdapter(FragmentManager fm, Company name) {
        super(fm);
        this.name = name;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch(position){
            case 0:
                RiskFragment risk = new RiskFragment();
                bundle.putString("risk", name.getRiskFactors());
                risk.setArguments(bundle);
                return risk;
            case 1:
                ExpenseFragment exp =  new ExpenseFragment();
                bundle.putString("exp", name.getExpenses());
                exp.setArguments(bundle);
                return exp;
            case 2:
                IncomeFragment income = new IncomeFragment();
                bundle.putString("inc", name.getIncome());
                income.setArguments(bundle);
                return income;
            case 3:
                ProfitFragment profit = new ProfitFragment();
                bundle.putString("exp", name.getExpenses());
                bundle.putString("inc", name.getIncome());
                profit.setArguments(bundle);
                return profit;
            case 4:
                OfficerFragment officer = new OfficerFragment();
                bundle.putString("off", name.getExecutiveOfficers());
                officer.setArguments(bundle);
                return officer;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return names[position];
    }
}
