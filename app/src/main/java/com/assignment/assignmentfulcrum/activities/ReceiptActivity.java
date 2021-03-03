package com.assignment.assignmentfulcrum.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.assignmentfulcrum.R;
import com.assignment.assignmentfulcrum.adapter.SalesAdapter;
import com.assignment.assignmentfulcrum.database.helper.SalesDatabaseHelper;
import com.assignment.assignmentfulcrum.database.model.SalesManagement;
import com.assignment.assignmentfulcrum.model.SalesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ReceiptActivity extends AppCompatActivity {
    private SalesDatabaseHelper db1;
    private RecyclerView receiptlist;
    private TextView txtsalestax, txttotal;
    private final ArrayList<SalesModel> sales_list = new ArrayList<>();
    double total_amount, total_sale_tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        db1 = new SalesDatabaseHelper(this);
        initView();
    }

    private void initView() {
        receiptlist = findViewById(R.id.receipt_list);
        txtsalestax = findViewById(R.id.sales_tax);
        txttotal = findViewById(R.id.total);

        getData();
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        List<SalesManagement> salesList = new ArrayList<>();
        salesList.addAll(db1.getAllSales());
        JSONArray jsonArray = new JSONArray();
        for (SalesManagement cn : salesList) {
            JSONObject sales = new JSONObject();
            try {
                sales.put("product", cn.getProduct());
                sales.put("price", cn.getPrice());
                sales.put("service_tax", cn.getServicetax());
                sales.put("import_duty", cn.getImportduty());
                sales.put("total", cn.getTotal());
                sales.put("total_tax", cn.getTotaltax());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(sales);
        }

        JSONObject salesObj = new JSONObject();
        try {
            salesObj.put("sales", jsonArray);
            salesObj.put("message", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arr1;
        if (!sales_list.isEmpty())
            sales_list.clear();
        try {
            arr1 = salesObj.getJSONArray("sales");
            for (int i = 0; i < arr1.length(); i++) {
                JSONObject obj = arr1.getJSONObject(i);
                sales_list.add(new SalesModel(obj.getString("product"),obj.getString("price"),obj.getString("service_tax"), obj.getString("import_duty"), obj.getString("total"), obj.getString("total_tax")));
                total_amount = total_amount + Double.parseDouble(obj.getString("total"));
                total_sale_tax = total_sale_tax + Double.parseDouble(obj.getString("total_tax"));
            }
            DecimalFormat df1 = new DecimalFormat("###.##");
            txtsalestax.setText("Sales Taxes : " + df1.format(total_sale_tax));
            txttotal.setText("Total : " + df1.format(total_amount));
            SalesAdapter salesAdapter = new SalesAdapter(this, sales_list);
            receiptlist.setHasFixedSize(true);
            receiptlist.setLayoutManager(new LinearLayoutManager(this));
            receiptlist.setAdapter(salesAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
