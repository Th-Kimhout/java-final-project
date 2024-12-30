package service;

import model.Sale_Detail;
import model.dto.CreateSaleDetailReq;
import model.dto.SaleDetailResponseDto;

import java.util.List;

public interface SaleDetailService {
    List<Sale_Detail> getSaleDetails();
    boolean addSaleDetail(CreateSaleDetailReq createSaleDetailReq);
    List<SaleDetailResponseDto> getSaleDetailBySaleId(int saleId);
}
