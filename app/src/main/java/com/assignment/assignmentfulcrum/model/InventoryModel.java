package com.assignment.assignmentfulcrum.model;

public class InventoryModel {
    private String product, price, type, imported;

    public InventoryModel(String product, String price, String type, String imported) {
        this.product = product;
        this.price = price;
        this.type = type;
        this.imported = imported;
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
