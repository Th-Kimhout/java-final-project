package service;

import model.Staff;
import model.dto.CreateStaffReq;
import model.dto.UpdateStaffReq;
import repository.CredentialLoader;
import repository.StaffRepoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    StaffRepoImpl staffRepoImpl = new StaffRepoImpl();

    @Override
    public List<Staff> getStaffs() {

        return staffRepoImpl.getAllStaffs();
    }

    @Override
    public boolean addStaff(CreateStaffReq createStaffReq) {
        return staffRepoImpl.addStaff(createStaffReq);
    }

    @Override
    public boolean updateStaff(int id, UpdateStaffReq updateStaffReq) {

        if (!(updateStaffReq.staffName().isEmpty()
                && updateStaffReq.staffGender().isEmpty()
                && updateStaffReq.staffDOB() == null
                && updateStaffReq.staffPosition().isEmpty()
                && updateStaffReq.isWorking() == null)) {
            if (staffRepoImpl.getAllStaffs().stream().anyMatch(staff -> staff.getId() == id)) {
                return staffRepoImpl.updateStaff(id, updateStaffReq);
            }
        }
        return false;
    }

    @Override
    public boolean deleteStaff(int id) {
        if (staffRepoImpl.getAllStaffs().stream().anyMatch(staff -> staff.getId() == id)) {
            return staffRepoImpl.deleteStaff(id);
        }
        return false;
    }

    @Override
    public Staff getStaff(int id) {
        return staffRepoImpl.getAllStaffs()
                .stream()
                .filter(staff -> staff.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Staff getStaff(String staffName) {
        return staffRepoImpl.getAllStaffs()
                .stream()
                .filter(staff -> staff.getStaff_name() == staffName)
                .findFirst()
                .orElse(null);
    }
}
