package repository;

import model.Staff;
import model.User;
import model.dto.CreateUserReq;
import model.dto.UpdateUserReq;
import utilies.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM users order by user_id asc";

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                users.add(new User(
                                rs.getInt("user_id"),
                                rs.getInt("staff_id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("role"),
                                rs.getBoolean("status")
                        )
                );
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addUser(CreateUserReq createUserReq) {
        String insertQuery = "INSERT INTO users (staff_id ,username , password , role, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        ) {
            preparedStatement.setInt(1, createUserReq.staffID());
            preparedStatement.setString(2, createUserReq.username());
            preparedStatement.setString(3, createUserReq.password());
            preparedStatement.setString(4, createUserReq.role());
            preparedStatement.setBoolean(5, true);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(int id, UpdateUserReq updateUserReq) {

        boolean isHaveUsername = !updateUserReq.username().isEmpty();
        boolean isHavePassword = !updateUserReq.password().isEmpty();
        boolean isHaveRole = !updateUserReq.role().isEmpty();
        boolean isActive = updateUserReq.status() != null;

        StringBuilder updateQuery = new StringBuilder("Update users SET ");
        boolean firstField = true;

        if (isHaveUsername) {
            updateQuery.append("username = ?");
            firstField = false;
        }

        if (isHavePassword) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("password = ?");
            firstField = false;
        }

        if (isHaveRole) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("role = ?");
            firstField = false;
        }
        if (isActive) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("status = ?");
        }


        updateQuery.append(" WHERE user_id = ?");
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery.toString());
        ) {
            int paramIndex = 1;
            if (isHaveUsername)
                preparedStatement.setString(paramIndex++, updateUserReq.username());
            if (isHavePassword)
                preparedStatement.setString(paramIndex++, updateUserReq.password());
            if (isHaveRole) preparedStatement.setString(paramIndex++, updateUserReq.role());
            if (isActive) preparedStatement.setBoolean(paramIndex++, updateUserReq.status());
            preparedStatement.setInt(paramIndex, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        String DeleteQuery = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(DeleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
