package repository;

import model.Sale;
import model.dto.CreateSaleReq;
import model.dto.SaleReportResponseDto;
import model.dto.UpdateVoidSaleReq;
import utilies.DBConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleRepoImpl implements SaleRepo {
    @Override
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String selectQuery = "SELECT * FROM sales";

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                sales.add(new Sale(
                                rs.getInt("id"),
                                rs.getDate("create_date"),
                                rs.getInt("staff_id"),
                                rs.getInt("customer_id"),
                                rs.getDouble("total_amount"),
                                rs.getString("status"),
                                rs.getString("void_reason"),
                                rs.getDate("void_date")
                        )
                );
            }
            return sales;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int addSale(CreateSaleReq createSaleReq) {
        String insertQuery = "INSERT INTO sales(staff_id, customer_id, status) VALUES (?,?,?) returning id";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        ) {
            preparedStatement.setInt(1, createSaleReq.staff_id());
            preparedStatement.setInt(2, createSaleReq.customer_id());
            preparedStatement.setString(3, "Completed");
            ResultSet rs = preparedStatement.executeQuery();

            int saleId = 0;
            if (rs.next()) {
                saleId = rs.getInt("id"); // Get the generated Sale ID
            }
            return saleId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean deleteSale(int id) {
        String deleteQuery = "DELETE FROM sales WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



    @Override
    public boolean voidSale(int id, UpdateVoidSaleReq updateVoidSaleReq) {
        String updateQuery = "UPDATE sales SET status = ?, void_reason = ?, void_date = ? WHERE id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, "VOIDED");
            preparedStatement.setString(2, updateVoidSaleReq.voidReason());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<SaleReportResponseDto> getSaleReportByDate(Date startDate, Date endDate) {
        List<SaleReportResponseDto> saleReportResponseDtos = new ArrayList<>();
        String selectDailySaleQuery = "SELECT " +
                "s.create_date AS sale_date, " +
                "SUM(sd.sale_quantity) AS total_products_sold, " +
                "SUM(sd.total_price) AS total_revenue_earned " +
                "FROM sales s " +
                "INNER JOIN sale_detail sd ON s.id = sd.sale_id " +
                "WHERE s.create_date BETWEEN ? AND ? " +
                "AND s.status != 'VOIDED'" +
                "GROUP BY s.create_date " +
                "ORDER BY sale_date";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(selectDailySaleQuery);
        ) {

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    saleReportResponseDtos.add(new SaleReportResponseDto(
                                    rs.getDate("sale_date"),
                                    rs.getInt("total_products_sold"),
                                    rs.getDouble("total_revenue_earned")
                            )

                    );
                }
                return saleReportResponseDtos;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
