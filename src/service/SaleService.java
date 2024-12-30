package service;

import model.dto.CreateSaleReq;
import model.dto.SaleReportResponseDto;
import model.dto.SaleResponseDto;
import model.dto.UpdateVoidSaleReq;

import java.sql.Date;
import java.util.List;

public interface SaleService {
    List<SaleResponseDto> getSales();
    int addSale(CreateSaleReq createSaleReq);
    boolean deleteSale(int id);
    Boolean voidSale(int id, UpdateVoidSaleReq updateVoidSaleReq);
    SaleResponseDto getSale(int id);
    List<SaleReportResponseDto> getSaleReport(Date startDate, Date endDate);
}
