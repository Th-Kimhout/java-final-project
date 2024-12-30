package model.dto;

import java.sql.Date;

public record CustomerResponseDto(
        int id,
        String customerName,
        Date customerDOB,
        String customerGender
) {
}
