package repository;

import model.Product;
import model.dto.CreateProductReq;
import model.dto.UpdateProductReq;
import utilies.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepoImpl implements ProductRepo {
    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT p.id, p.product_name, c.category_name, p.product_quantity, p.product_price " +
                "FROM products p " +
                "inner join categories c on p.product_category = c.id " +
                "order by p.id asc";

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("product_name"),
                                rs.getString("category_name"),
                                rs.getInt("product_quantity"),
                                rs.getDouble("product_price")
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public boolean addProduct(CreateProductReq createProduct) {
        String insertQuery = "Insert into products(product_name, product_category,product_quantity,product_price) values(?,?,?,?)";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, createProduct.product_name());
            preparedStatement.setInt(2, createProduct.product_category());
            preparedStatement.setInt(3, createProduct.product_quantity());
            preparedStatement.setDouble(4, createProduct.product_price());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateProduct(int id, UpdateProductReq updateProduct) {

        boolean isHaveProductName = !updateProduct.product_name().isEmpty();
        boolean isHaveProductCategory = updateProduct.product_category() != -1;
        boolean isHaveProductQuantity = updateProduct.product_quantity() != -1;
        boolean isHaveProductPrice = updateProduct.product_price() > 0;

        StringBuilder updateQuery = new StringBuilder("UPDATE products SET ");
        boolean firstField = true;

        if (isHaveProductName) {
            updateQuery.append("product_name = ?");
            firstField = false;
        }

        if (isHaveProductCategory) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("product_category = ?");
            firstField = false;
        }

        if (isHaveProductQuantity) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("product_quantity = ?");
            firstField = false;
        }

        if (isHaveProductPrice) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("product_price = ?");
        }

        updateQuery.append(" WHERE id = ?");

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery.toString())) {

            int paramIndex = 1;

            if (isHaveProductName) preparedStatement.setString(paramIndex++, updateProduct.product_name());

            if (isHaveProductCategory) preparedStatement.setInt(paramIndex++, updateProduct.product_category());

            if (isHaveProductQuantity) preparedStatement.setInt(paramIndex++, updateProduct.product_quantity());

            if (isHaveProductPrice) preparedStatement.setDouble(paramIndex++, updateProduct.product_price());

            preparedStatement.setInt(paramIndex, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        String deleteQuery = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
