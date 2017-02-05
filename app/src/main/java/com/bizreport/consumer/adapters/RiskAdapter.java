package com.bizreport.consumer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizreport.consumer.R;

import java.util.ArrayList;

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.ViewHolder> {
    ArrayList<String> risks;

    public RiskAdapter(ArrayList<String> risks){
        this.risks = risks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.risk.setText(risks.get(position));
    }

    @Override
    public int getItemCount() {
        return risks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView risk;
        public ViewHolder(View itemView) {
            super(itemView);
            risk = (TextView)itemView.findViewById(R.id.list_item);
        }
    }
}
