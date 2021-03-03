package com.assignment.assignmentfulcrum.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.assignmentfulcrum.R;
import com.assignment.assignmentfulcrum.model.SalesModel;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {
    private final ArrayList<SalesModel> modelArrayList;
    Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_product;
        public TextView txt_price;

        public MyViewHolder(View view) {
            super(view);
            txt_product =  view.findViewById(R.id.txt_product);
            txt_price =  view.findViewById(R.id.txt_price);
        }
    }

    public SalesAdapter(Activity activity, ArrayList<SalesModel> modelArrayList) {
        this.activity = activity;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public SalesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_item, parent, false);
        return new SalesAdapter.MyViewHolder(itemView);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SalesAdapter.MyViewHolder holder, int position) {
        holder.txt_product.setText(modelArrayList.get(position).getProduct());
        holder.txt_price.setText(modelArrayList.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
}