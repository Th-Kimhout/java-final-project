package model.dto;

public record CreateSaleDetailReq(int sale_id, int product_id, double unit_price, int sale_quantity, double discount_percent) {
}
