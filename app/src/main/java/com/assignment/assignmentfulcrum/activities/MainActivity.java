package com.assignment.assignmentfulcrum.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.assignment.assignmentfulcrum.R;
import com.assignment.assignmentfulcrum.adapter.InventoryAdapter;
import com.assignment.assignmentfulcrum.database.helper.InventoryDatabaseHelper;
import com.assignment.assignmentfulcrum.database.helper.SalesDatabaseHelper;
import com.assignment.assignmentfulcrum.database.model.InventoryManagement;
import com.assignment.assignmentfulcrum.database.model.SalesManagement;
import com.assignment.assignmentfulcrum.model.InventoryModel;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private InventoryDatabaseHelper db;
    private SalesDatabaseHelper db1;
    private RelativeLayout layout_main;
    private RecyclerView inventorylist1;
    private final ArrayList<InventoryModel> inventory_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new InventoryDatabaseHelper(this);
        db1 = new SalesDatabaseHelper(this);
        boolean value = db.getAllInventory().isEmpty();
        if (value) {
            db.insertInventory("1 book @12.49", "12.49", "book", "false");
            db.insertInventory("1 music CD @14.99", "14.99", "music", "false");
            db.insertInventory("1 chocolate bar @0.85", "0.85", "food", "false");
            db.insertInventory("1 imported box of chocolates @10.00", "10.00", "food", "true");
            db.insertInventory("1 imported bottle of perfume @47.50", "47.50", "cosmetic", "true");
            db.insertInventory("1 imported bottle of perfume @27.99", "27.99", "cosmetic", "true");
            db.insertInventory("1 bottle of perfume @18.99", "18.99", "cosmetic", "false");
            db.insertInventory("1 packet of headache pills @9.75", "9.75", "medicine", "false");
            db.insertInventory("1 box of imported chocolates @11.25", "11.25", "food", "true");
            initView();
        } else {
            initView();
        }
    }

    private void initView() {
        layout_main = findViewById(R.id.layout_main);
        inventorylist1 = findViewById(R.id.inventory_list);
        LinearLayout btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(view -> {
            boolean value = db1.getAllSales().isEmpty();
            if (value) {
                Snackbar.make(layout_main, getResources().getString(R.string.please_select_atleast_one_item), Snackbar.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(this, ReceiptActivity.class);
                startActivity(i);
            }
        });
        getData();
    }

    private void getData() {
        List<InventoryManagement> inventoryList = new ArrayList<>();
        inventoryList.addAll(db.getAllInventory());
        JSONArray jsonArray = new JSONArray();
        for (InventoryManagement cn : inventoryList) {
            JSONObject inventory = new JSONObject();
            try {
                inventory.put("product", cn.getProduct());
                inventory.put("price", cn.getPrice());
                inventory.put("type", cn.getType());
                inventory.put("imported", cn.getImported());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(inventory);
        }

        JSONObject inventoryObj = new JSONObject();
        try {
            inventoryObj.put("inventory", jsonArray);
            inventoryObj.put("message", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arr1;
        if (!inventory_list.isEmpty())
            inventory_list.clear();
        try {
            arr1 = inventoryObj.getJSONArray("inventory");
            for (int i = 0; i < arr1.length(); i++) {
                JSONObject obj = arr1.getJSONObject(i);
                inventory_list.add(new InventoryModel(obj.getString("product"),obj.getString("price"),obj.getString("type"), obj.getString("imported")));
            }
            InventoryAdapter inventoryAdapter = new InventoryAdapter(this, inventory_list);
            inventorylist1.setHasFixedSize(true);
            inventorylist1.setLayoutManager(new LinearLayoutManager(this));
            inventorylist1.setAdapter(inventoryAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        db1.delete();
    }
}