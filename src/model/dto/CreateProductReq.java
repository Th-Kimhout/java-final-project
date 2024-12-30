package model.dto;

public record CreateProductReq(String product_name, int product_category, int product_quantity, double product_price) {
}
