package model.dto;

public record ProductResponseDto(
        int productId,
        String productName,
        String productCategory,
        int productQuantity,
        double productPrice) {
}
