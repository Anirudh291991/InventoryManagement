package com.assignment.assignmentfulcrum.model;

public class SalesModel {
    private String product, price, service_tax, import_duty, total, total_tax;

    public SalesModel(String product, String price, String service_tax, String import_duty, String total, String total_tax) {
        this.product = product;
        this.price = price;
        this.service_tax = service_tax;
        this.import_duty = import_duty;
        this.total = total;
        this.total_tax = total_tax;
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

    public String getService_tax() {
        return service_tax;
    }

    public void setService_tax(String service_tax) {
        this.service_tax = service_tax;
    }

    public String getImport_duty() {
        return import_duty;
    }

    public void setImport_duty(String import_duty) {
        this.import_duty = import_duty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(String total_tax) {
        this.total_tax = total_tax;
    }
}
