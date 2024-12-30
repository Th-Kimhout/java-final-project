package controller;

import model.Sale_Detail;
import model.dto.CreateSaleDetailReq;
import model.dto.SaleDetailResponseDto;
import service.SaleDetailServiceImpl;

import java.util.List;

public class SaleDetailController {
    SaleDetailServiceImpl saleDetailService = new SaleDetailServiceImpl();

    public List<Sale_Detail> getSaleDetail() {
        return saleDetailService.getSaleDetails();
    }

    public boolean addSaleDetail(CreateSaleDetailReq createSaleDetailReq) {
        return saleDetailService.addSaleDetail(createSaleDetailReq);
    }

    public List<SaleDetailResponseDto> getSaleDetailById(int id) {
        return saleDetailService.getSaleDetailBySaleId(id);
    }
}
