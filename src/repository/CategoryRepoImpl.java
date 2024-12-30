package repository;

import model.Category;
import utilies.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepoImpl implements CategoryRepo {
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT * FROM categories order by id asc";

        try (Connection conn = DBConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("category_name")));
            }
            return categories;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addCategory(String category_name) {
        String insertQuery = "INSERT INTO categories(category_name) VALUES (?)";
        try (Connection conn = DBConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setString(1, category_name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateCategory(int id, String category_name) {
        String updateQuery = "Update categories set category_name = ? where id = ?";

        try (Connection conn = DBConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            if (!category_name.isEmpty()) ps.setString(1, category_name);
            ps.setInt(2, id);
            if (category_name.isEmpty()) {
                return false;
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCategoryByID(int id) {
        String deleteQuery = "delete from categories where id = ?";

        try (Connection conn = DBConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

