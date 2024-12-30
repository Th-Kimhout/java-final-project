package model.dto;

import java.sql.Date;

public record SaleResponseDto(
        int id,
        int staffID,
        int customerID,
        Date saleDate,
        double totalSale,
        String status,
        String voidReason,
        Date voidDate) {
}
