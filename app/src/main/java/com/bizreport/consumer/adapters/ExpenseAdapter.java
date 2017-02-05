package com.bizreport.consumer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizreport.consumer.R;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    ArrayList<String> expenses;

    public ExpenseAdapter(ArrayList<String> expenses){
        this.expenses = expenses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int month = position + 1;
        holder.expense.setText("Month " + Integer.parseInt(""+month) + ": " + "$" + expenses.get(position));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView expense;
        public ViewHolder(View itemView) {
            super(itemView);
            expense = (TextView)itemView.findViewById(R.id.list_item);
        }
    }
}

