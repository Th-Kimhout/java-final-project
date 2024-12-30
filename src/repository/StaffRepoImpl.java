package repository;

import model.Staff;
import model.dto.CreateStaffReq;
import model.dto.UpdateStaffReq;
import utilies.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffRepoImpl implements StaffRepo {
    @Override
    public List<Staff> getAllStaffs() {
        List<Staff> staffs = new ArrayList<>();
        String selectQuery = "SELECT * FROM staffs order by id asc";

        try (Connection conn = DBConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                staffs.add(new Staff(rs.getInt("id"), rs.getString("staff_name"), rs.getDate("staff_dob"), rs.getString("staff_gender"), rs.getString("staff_position"), rs.getBoolean("is_working")));
            }
            return staffs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addStaff(CreateStaffReq createStaff) {
        String insertQuery = "INSERT INTO staffs(staff_name, staff_dob, staff_gender, staff_position, is_working) values (?,?,?,?,?)";

        try (Connection conn = DBConfig.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, createStaff.staffName());
            preparedStatement.setDate(2, createStaff.staff_dob());
            preparedStatement.setString(3, createStaff.staff_gender());
            preparedStatement.setString(4, createStaff.staff_position());
            preparedStatement.setBoolean(5, true);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateStaff(int id, UpdateStaffReq updateStaff) {

        boolean isHaveStaffName = !updateStaff.staffName().isEmpty();
        boolean isHaveStaffDOB = !(updateStaff.staffDOB() == null);
        boolean isHaveStaffGender = !updateStaff.staffGender().isEmpty();
        boolean isHaveStaffPosition = !updateStaff.staffPosition().isEmpty();
        Boolean isHaveIsWorking = updateStaff.isWorking();

        StringBuilder updateQuery = new StringBuilder("UPDATE staffs SET ");
        boolean firstField = true;

        if (!isHaveStaffName) {
            updateQuery.append("staff_name = ?");
            firstField = false;
        }

        if (!isHaveStaffDOB) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("staff_dob = ?");
            firstField = false;
        }

        if (!isHaveStaffGender) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("staff_gender = ?");
            firstField = false;
        }
        if (!isHaveStaffPosition) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("staff_position = ?");
            firstField = false;
        }
        if (isHaveIsWorking != null) {
            if (!firstField) updateQuery.append(", ");
            updateQuery.append("is_working = ?");
        }


        updateQuery.append(" WHERE id = ?");

        try (Connection conn = DBConfig.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(updateQuery.toString())) {
            int paramIndex = 1;

            if (isHaveStaffDOB)
                if (isHaveStaffName) preparedStatement.setString(paramIndex++, updateStaff.staffName());
            if (isHaveStaffDOB) preparedStatement.setDate(paramIndex++, updateStaff.staffDOB());
            if (isHaveStaffGender) preparedStatement.setString(paramIndex++, updateStaff.staffGender());
            if (isHaveStaffPosition) preparedStatement.setString(paramIndex++, updateStaff.staffPosition());
            if (isHaveIsWorking != null) preparedStatement.setBoolean(paramIndex++, updateStaff.isWorking());
            preparedStatement.setInt(paramIndex, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteStaff(int id) {
        String deleteQuery = "DELETE FROM staffs WHERE id = ?";
        try (Connection conn = DBConfig.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

