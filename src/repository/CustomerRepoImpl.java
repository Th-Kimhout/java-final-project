package repository;

import model.Customer;
import model.dto.CreateCustomerReq;
import model.dto.UpdateCustomerReq;
import utilies.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements CustomerRepo {

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String selectQuery = "SELECT * FROM customers order by id asc";

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                customers.add(new Customer(
                                rs.getInt("id"),
                                rs.getString("customer_name"),
                                rs.getDate("customer_dob"),
                                rs.getString("customer_gender")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public boolean addCustomer(CreateCustomerReq createCustomer) {

        String insertQuery = "INSERT INTO customers (customer_name, customer_dob, customer_gender) VALUES (?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, createCustomer.customer_name());
            preparedStatement.setDate(2, createCustomer.customer_dob());
            preparedStatement.setString(3, createCustomer.customer_gender());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateCustomer(int id, UpdateCustomerReq updateCustomer) {

        boolean isHaveCustomerName = !updateCustomer.customer_name().isEmpty();
        boolean isHaveCustomerDOB = updateCustomer.customer_dob() != null;
        boolean isHaveCustomerGender = !updateCustomer.customer_gender().isEmpty();


        StringBuilder updateQuery = new StringBuilder("UPDATE customers SET ");
        boolean firstField = true;

        if (isHaveCustomerName) {
            updateQuery.append("customer_name = ?");
            firstField = false;
        }

        if (isHaveCustomerDOB) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("customer_dob = ?");
            firstField = false;
        }

        if (isHaveCustomerGender) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("customer_gender = ?");
        }

        updateQuery.append(" WHERE id = ?");

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery.toString())) {

            int paramIndex = 1;
            if (!updateCustomer.customer_name().isEmpty())
                preparedStatement.setString(paramIndex++, updateCustomer.customer_name());

            if (updateCustomer.customer_dob() != null)
                preparedStatement.setDate(paramIndex++, updateCustomer.customer_dob());

            if (!updateCustomer.customer_gender().isEmpty())
                preparedStatement.setString(paramIndex++, updateCustomer.customer_gender());

            preparedStatement.setInt(paramIndex, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        if (id != 0) {
            String deleteQuery = "DELETE FROM customers WHERE id = ?";

            try (Connection conn = DBConfig.getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Cannot delete Default Customer");
        }
        return false;
    }

}

