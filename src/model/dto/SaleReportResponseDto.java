package model.dto;

import java.sql.Date;

public record SaleReportResponseDto(Date createDate,
                                    int totalProductsSold,
                                    double totalEarn) {
}
