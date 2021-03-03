package com.assignment.assignmentfulcrum.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.assignment.assignmentfulcrum.database.model.InventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class InventoryDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventory_db";

    public InventoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryManagement.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + InventoryManagement.TABLE_NAME);
        onCreate(db);
    }

    public long insertInventory(String product, String price, String type, String imported) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryManagement.COLUMN_PRODUCT, product);
        values.put(InventoryManagement.COLUMN_PRICE, price);
        values.put(InventoryManagement.COLUMN_TYPE, type);
        values.put(InventoryManagement.COLUMN_IMPORTED, imported);
        long id = db.insert(InventoryManagement.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<InventoryManagement> getAllInventory() {
        List<InventoryManagement> inventory = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + InventoryManagement.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                InventoryManagement inventory1 = new InventoryManagement();
                inventory1.setProduct(cursor.getString(cursor.getColumnIndex(InventoryManagement.COLUMN_PRODUCT)));
                inventory1.setPrice(cursor.getString(cursor.getColumnIndex(InventoryManagement.COLUMN_PRICE)));
                inventory1.setType(cursor.getString(cursor.getColumnIndex(InventoryManagement.COLUMN_TYPE)));
                inventory1.setImported(cursor.getString(cursor.getColumnIndex(InventoryManagement.COLUMN_IMPORTED)));
                inventory.add(inventory1);
            } while (cursor.moveToNext());
        }
        db.close();
        return inventory;
    }
}
