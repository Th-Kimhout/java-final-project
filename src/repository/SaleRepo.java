package repository;

import model.Sale;
import model.dto.CreateSaleReq;
import model.dto.SaleReportResponseDto;
import model.dto.UpdateVoidSaleReq;

import java.sql.Date;
import java.util.List;

public interface SaleRepo {
    List<Sale> getAllSales();
    int addSale(CreateSaleReq createSaleReq);
    boolean deleteSale(int id);
    boolean voidSale(int id, UpdateVoidSaleReq updateVoidSaleReq);
    List<SaleReportResponseDto> getSaleReportByDate(Date startDate , Date endDate);
}
