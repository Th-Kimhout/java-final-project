package model;

import lombok.Data;

@Data

public class Sale_Detail {
    private int sale_id;
    private int product_id;
    private String product_name;
    private double unitPrice;
    private int saleQuantity;
    private double discountPercent;
    private double totalPrice;
    private String status;

    public Sale_Detail() {
    }

    public Sale_Detail(int sale_id, int product_id, String product_name, double unitPrice, int saleQuantity, double discountPercent, double totalPrice, String status) {
        this.sale_id = sale_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.unitPrice = unitPrice;
        this.saleQuantity = saleQuantity;
        this.discountPercent = discountPercent;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
