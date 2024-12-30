package service;

import model.Sale;
import model.dto.CreateSaleReq;
import model.dto.SaleReportResponseDto;
import model.dto.SaleResponseDto;
import model.dto.UpdateVoidSaleReq;
import repository.SaleRepoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SaleServiceImpl implements SaleService {
    SaleRepoImpl saleRepo = new SaleRepoImpl();

    @Override
    public List<SaleResponseDto> getSales() {
        List<SaleResponseDto> saleResponseDto = new ArrayList<>();
        List<Sale> sales = saleRepo.getAllSales();
        if (sales != null) {
            for (Sale sale : sales) {
                saleResponseDto.add(new SaleResponseDto(
                        sale.getId(),
                        sale.getStaff_id(),
                        sale.getCustomer_id(),
                        sale.getCreate_date(),
                        sale.getTotal_amount(),
                        sale.getStatus(),
                        sale.getVoid_reason(),
                        sale.getVoid_date()
                ));

            }
        }
        return saleResponseDto.stream().sorted(Comparator.comparing(SaleResponseDto::id)).collect(Collectors.toList());
    }

    @Override
    public int addSale(CreateSaleReq createSaleReq) {
        return saleRepo.addSale(createSaleReq);
    }

    @Override
    public boolean deleteSale(int id) {
        if (saleRepo.getAllSales().stream().anyMatch(s -> s.getId() == id)) {
            return saleRepo.deleteSale(id);
        }
        return false;
    }

    @Override
    public Boolean voidSale(int id, UpdateVoidSaleReq updateVoidSaleReq) {
        if (saleRepo.getAllSales().stream().anyMatch(s -> s.getId() == id)) {
            return saleRepo.voidSale(id, updateVoidSaleReq);
        }
        return false;
    }

    @Override
    public SaleResponseDto getSale(int id) {
        SaleResponseDto saleResponseDto = null;
        Sale sale = saleRepo.getAllSales().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (sale != null) {
            saleResponseDto = new SaleResponseDto(
                    sale.getId(),
                    sale.getStaff_id(),
                    sale.getCustomer_id(),
                    sale.getCreate_date(),
                    sale.getTotal_amount(),
                    sale.getStatus(),
                    sale.getVoid_reason(),
                    sale.getVoid_date()
            );
        }
        return saleResponseDto;
    }

    @Override
    public List<SaleReportResponseDto> getSaleReport(Date startDate, Date endDate) {
        return saleRepo.getSaleReportByDate(startDate, endDate);
    }
}
