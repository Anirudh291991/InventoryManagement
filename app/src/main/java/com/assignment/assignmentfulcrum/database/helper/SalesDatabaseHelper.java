package com.assignment.assignmentfulcrum.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.assignment.assignmentfulcrum.database.model.SalesManagement;

import java.util.ArrayList;
import java.util.List;

public class SalesDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sales_db";

    public SalesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SalesManagement.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SalesManagement.TABLE_NAME);
        onCreate(db);
    }

    public long insertSales(String product, String price, String service_tax, String import_duty, String total, String total_tax) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SalesManagement.COLUMN_PRODUCT, product);
        values.put(SalesManagement.COLUMN_PRICE, price);
        values.put(SalesManagement.COLUMN_SERVICE_TAX, service_tax);
        values.put(SalesManagement.COLUMN_IMPORT_DUTY, import_duty);
        values.put(SalesManagement.COLUMN_TOTAL, total);
        values.put(SalesManagement.COLUMN_TOTAL_TAX, total_tax);
        long id = db.insert(SalesManagement.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void deleteSalesItem(String product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SalesManagement.TABLE_NAME, SalesManagement.COLUMN_PRODUCT + " = '" + product + "'",
                null);
        db.close();
    }

    public List<SalesManagement> getAllSales() {
        List<SalesManagement> sales = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + SalesManagement.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SalesManagement sales1 = new SalesManagement();
                sales1.setProduct(cursor.getString(cursor.getColumnIndex(SalesManagement.COLUMN_PRODUCT)));
                sales1.setPrice(cursor.getString(cursor.getColumnIndex(SalesManagement.COLUMN_PRICE)));
                sales1.setServicetax(cursor.getString(cursor.getColumnIndex(SalesManagement.COLUMN_SERVICE_TAX)));
                sales1.setImportduty(cursor.getString(cursor.getColumnIndex(SalesManagement.COLUMN_IMPORT_DUTY)));
                sales1.setTotal(cursor.getString(cursor.getColumnIndex(SalesManagement.COLUMN_TOTAL)));
                sales1.setTotaltax(cursor.getString(cursor.getColumnIndex(SalesManagement.COLUMN_TOTAL_TAX)));
                sales.add(sales1);
            } while (cursor.moveToNext());
        }
        db.close();
        return sales;
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SalesManagement.TABLE_NAME, null, null);
        db.close();
    }
}

