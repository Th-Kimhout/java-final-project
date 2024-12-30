package repository;

import model.Sale_Detail;
import model.dto.CreateSaleDetailReq;
import utilies.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailRepoImpl implements SaleDetailRepo {
    @Override
    public List<Sale_Detail> getAllSaleDetail() {
        List<Sale_Detail> saleDetails = new ArrayList<>();
        String selectQuery = "select sale_id, product_id, unit_price, sale_quantity, discount_percent, total_price, sd.status,p.product_name " +
                "from sale_detail sd " +
                "inner join products p on sd.product_id = p.id";

        try (Connection conn = DBConfig.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(selectQuery)) {
            while (rs.next()) {
                saleDetails.add(new Sale_Detail(
                        rs.getInt("sale_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("sale_quantity"),
                        rs.getDouble("discount_percent"),
                        rs.getDouble("total_price"),
                        rs.getString("status")
                ));
            }
            return saleDetails;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return saleDetails;
    }

    @Override
    public boolean addSaleDetail(CreateSaleDetailReq createSaleDetailReq) {
        String insertQuery = "INSERT INTO sale_detail(sale_id, product_id, unit_price, sale_quantity, discount_percent, status) values(?,?,?,?,?,?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)
        ) {
            preparedStatement.setInt(1, createSaleDetailReq.sale_id());
            preparedStatement.setInt(2, createSaleDetailReq.product_id());
            preparedStatement.setDouble(3, createSaleDetailReq.unit_price());
            preparedStatement.setInt(4, createSaleDetailReq.sale_quantity());
            preparedStatement.setDouble(5, createSaleDetailReq.discount_percent());
            preparedStatement.setString(6, "Completed");
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Sale_Detail> getSaleDetailBySaleId(int saleId) {

        List<Sale_Detail> saleDetails = new ArrayList<>();
        String selectQuery = "select sale_id, product_id, p.product_name, unit_price, sale_quantity, discount_percent, total_price, sd.status " +
                "from sale_detail sd " +
                "inner join products p on sd.product_id = p.id " +
                "where sale_id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, saleId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    saleDetails.add(new Sale_Detail(
                            rs.getInt("sale_id"),
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getDouble("unit_price"),
                            rs.getInt("sale_quantity"),
                            rs.getDouble("discount_percent"),
                            rs.getDouble("total_price"),
                            rs.getString("status")
                    ));
                }
                return saleDetails;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return saleDetails;
    }
}
