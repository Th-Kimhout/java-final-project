package model.dto;

public record SaleDetailResponseDto(
        String productName,
        double unitPrice,
        int saleQty,
        double discountPercent,
        double totalPrice) {

}
