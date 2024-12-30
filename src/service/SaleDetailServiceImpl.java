package service;

import model.Sale_Detail;
import model.dto.CreateSaleDetailReq;
import model.dto.SaleDetailResponseDto;
import repository.SaleDetailRepoImpl;

import java.util.ArrayList;
import java.util.List;

public class SaleDetailServiceImpl implements SaleDetailService {
    SaleDetailRepoImpl saleDetailRepo = new SaleDetailRepoImpl();

    @Override
    public List<Sale_Detail> getSaleDetails() {
        return saleDetailRepo.getAllSaleDetail();
    }

    @Override
    public boolean addSaleDetail(CreateSaleDetailReq createSaleDetailReq) {
        return saleDetailRepo.addSaleDetail(createSaleDetailReq);
    }

    @Override
    public List<SaleDetailResponseDto> getSaleDetailBySaleId(int saleId) {
        List<Sale_Detail> saleDetails = saleDetailRepo.getSaleDetailBySaleId(saleId);
        List<SaleDetailResponseDto> saleDetailResponseDtos = new ArrayList<>();
        for (Sale_Detail saleDetail : saleDetails) {
            saleDetailResponseDtos.add(new SaleDetailResponseDto(
                    saleDetail.getProduct_name(),
                    saleDetail.getUnitPrice(),
                    saleDetail.getSaleQuantity(),
                    saleDetail.getDiscountPercent(),
                    saleDetail.getTotalPrice()));
            return saleDetailResponseDtos;
        }
        return saleDetailResponseDtos;
    }
}
