package model.dto;

import java.sql.Date;

public record SaleDetailVoidedResponseDto(
        int saleID,
        String staffName,
        String customerName,
        String productName,
        double unitPrice,
        int saleQty,
        double discountPercent,
        double totalPrice,
        String status,
        String voidReason,
        Date voidDate) {

}
