package com.assignment.assignmentfulcrum.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.assignmentfulcrum.R;
import com.assignment.assignmentfulcrum.database.helper.SalesDatabaseHelper;
import com.assignment.assignmentfulcrum.model.InventoryModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {
    private final ArrayList<InventoryModel> modelArrayList;
    Activity activity;
    private SalesDatabaseHelper db;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkbox;
        public TextView txt_product;

        public MyViewHolder(View view) {
            super(view);
            checkbox =  view.findViewById(R.id.checkbox);
            txt_product =  view.findViewById(R.id.txt_product);
        }
    }

    public InventoryAdapter(Activity activity, ArrayList<InventoryModel> modelArrayList) {
        this.activity = activity;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public InventoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(InventoryAdapter.MyViewHolder holder, int position) {
        db = new SalesDatabaseHelper(activity);

        holder.txt_product.setText(modelArrayList.get(position).getProduct());

        holder.checkbox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked) {
                insertProductForSales(modelArrayList.get(position).getProduct(), modelArrayList.get(position).getPrice(), modelArrayList.get(position).getType(), modelArrayList.get(position).getImported());
            } else {
                db.deleteSalesItem(modelArrayList.get(position).getProduct());
            }
        });

    }

    private void insertProductForSales(String product, String price, String type, String imported) {
        double price1 = Double.parseDouble(price);
        double service_tax;
        double import_tax;
        DecimalFormat df = new DecimalFormat("###.#");
        DecimalFormat df1 = new DecimalFormat("###.##");
        double total;
        double total_tax;

        if (imported.equals("true")) {
            if (type.equals("food") || type.equals("medicine") || type.equals("book")) {
                import_tax = (double) Math.round(price1 * 5) / 100;
                total = price1 + Double.parseDouble(df.format(import_tax));
                total_tax = Double.parseDouble(df.format(import_tax));
                db.insertSales(product, price, "0.00", String.valueOf(import_tax), String.valueOf(total), String.valueOf(total_tax));
            } else {
                service_tax = (double) Math.round(price1 * 10) / 100;
                import_tax = (double) Math.round(price1 * 5) / 100;
                total = price1 + service_tax + Double.parseDouble(df.format(import_tax));
                total_tax = service_tax + Double.parseDouble(df.format(import_tax));
                db.insertSales(product, price, String.valueOf(service_tax), String.valueOf(import_tax), String.valueOf(total), String.valueOf(total_tax));
            }
        } else {
            if (type.equals("food") || type.equals("medicine") || type.equals("book")) {
                db.insertSales(product, price, "0.00", "0.00", String.valueOf(price1), "0.00");
            } else {
                service_tax = (double) Math.round(price1 * 10) / 100;
                total = price1 + service_tax;
                db.insertSales(product, price, String.valueOf(service_tax), "0.00", df1.format(total), String.valueOf(service_tax));
            }

        }
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
}

