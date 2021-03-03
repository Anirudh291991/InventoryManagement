package com.assignment.assignmentfulcrum.database.model;

public class SalesManagement {
    public static final String TABLE_NAME = "sales";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SERVICE_TAX = "service_tax";
    public static final String COLUMN_IMPORT_DUTY = "import_duty";
    public static final String COLUMN_TOTAL = "total";
    public static final String COLUMN_TOTAL_TAX = "total_tax";

    private int id;
    private String product;
    private String price;
    private String service_tax;
    private String import_duty;
    private String total;
    private String total_tax;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT + " TEXT,"
                    + COLUMN_PRICE + " TEXT,"
                    + COLUMN_SERVICE_TAX + " TEXT,"
                    + COLUMN_IMPORT_DUTY + " TEXT,"
                    + COLUMN_TOTAL + " TEXT,"
                    + COLUMN_TOTAL_TAX + " TEXT"
                    + ")";

    public SalesManagement(){}

    public SalesManagement(int id, String product, String price, String service_tax, String import_duty, String total, String total_tax){
        this.id = id;
        this.product = product;
        this.price = price;
        this.service_tax = service_tax;
        this.import_duty = import_duty;
        this.total = total;
        this.total_tax = total_tax;
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

    public String getServicetax() {
        return service_tax;
    }

    public void setServicetax(String service_tax) {
        this.service_tax = service_tax;
    }

    public String getImportduty() {
        return import_duty;
    }

    public void setImportduty(String import_duty) {
        this.import_duty = import_duty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotaltax() {
        return total_tax;
    }

    public void setTotaltax(String total_tax) {
        this.total_tax = total_tax;
    }
}

