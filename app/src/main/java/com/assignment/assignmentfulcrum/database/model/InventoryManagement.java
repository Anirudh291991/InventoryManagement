package com.assignment.assignmentfulcrum.database.model;

public class InventoryManagement {
    public static final String TABLE_NAME = "inventory";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_IMPORTED = "imported";

    private int id;
    private String product;
    private String price;
    private String type;
    private String imported;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT + " TEXT,"
                    + COLUMN_PRICE + " TEXT,"
                    + COLUMN_TYPE + " TEXT,"
                    + COLUMN_IMPORTED + " TEXT"
                    + ")";

    public InventoryManagement(){}

    public InventoryManagement(int id, String product, String price, String type, String imported){
        this.id = id;
        this.product = product;
        this.price = price;
        this.type = type;
        this.imported = imported;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImported() {
        return imported;
    }

    public void setImported(String imported) {
        this.imported = imported;
    }
}
