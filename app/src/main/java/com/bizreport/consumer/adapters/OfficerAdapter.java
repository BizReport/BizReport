package com.bizreport.consumer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizreport.consumer.R;

import java.util.ArrayList;

public class OfficerAdapter extends RecyclerView.Adapter<OfficerAdapter.ViewHolder> {
    ArrayList<String> officers;

    public OfficerAdapter(ArrayList<String> expenses){
        this.officers = expenses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.officer.setText(officers.get(position));
    }

    @Override
    public int getItemCount() {
        return officers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView officer;
        public ViewHolder(View itemView) {
            super(itemView);
            officer = (TextView)itemView.findViewById(R.id.list_item);
        }
    }
}

