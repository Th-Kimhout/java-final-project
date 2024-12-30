package controller;

import model.Sale;
import model.dto.CreateSaleReq;
import model.dto.SaleReportResponseDto;
import model.dto.SaleResponseDto;
import model.dto.UpdateVoidSaleReq;
import service.SaleServiceImpl;

import java.sql.Date;
import java.util.List;

public class SaleController {
    SaleServiceImpl saleService = new SaleServiceImpl();
    public List<SaleResponseDto> getSales() {
        return saleService.getSales();
    }
    public int addSale(CreateSaleReq createSaleReq) {
        return saleService.addSale(createSaleReq);
    }

    public boolean voidSale(int id, UpdateVoidSaleReq updateVoidSaleReq) {
        return saleService.voidSale(id , updateVoidSaleReq);
    }

    public boolean deleteSale(int id) {
        return saleService.deleteSale(id);
    }
    public SaleResponseDto getSale(int id) {
        return saleService.getSale(id);
    }
    public List<SaleReportResponseDto> getSaleReport(Date startDate, Date endDate) {
        return saleService.getSaleReport(startDate, endDate);
    }
}
