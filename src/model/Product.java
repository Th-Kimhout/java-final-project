package model;

import lombok.Data;

@Data

public class Product {
    private int id;
    private String product_name;
    private String product_category;
    private int product_quantity;
    private double product_price;

    public Product() {}

    public Product(int id, String product_name, String product_category, int product_quantity, double product_price) {
        this.id = id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "ID = " + id +
                "\nProduct's Name = " + product_name +
                "\nProduct's Category = " + product_category +
                "\nProduct's Quantity = " + product_quantity +
                "\nProduct's Price = " + product_price;
    }
}
