package repository;

import model.Sale_Detail;
import model.dto.CreateSaleDetailReq;

import java.util.List;

public interface SaleDetailRepo {
    List<Sale_Detail> getAllSaleDetail();
    boolean addSaleDetail(CreateSaleDetailReq createSaleDetailReq);
    List<Sale_Detail> getSaleDetailBySaleId(int saleId);

}
