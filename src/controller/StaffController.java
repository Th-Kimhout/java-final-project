package controller;

import model.Staff;
import model.dto.CreateSaleReq;
import model.dto.CreateStaffReq;
import model.dto.UpdateStaffReq;
import service.StaffServiceImpl;

import java.util.List;

public class StaffController {
    StaffServiceImpl staffService = new StaffServiceImpl();
    public List<Staff> getAllStaff() {
        return staffService.getStaffs();
    }

    public boolean addStaff(CreateStaffReq createStaffReq) {
        return staffService.addStaff(createStaffReq);
    }
    public boolean deleteStaff(int id) {
        return staffService.deleteStaff(id);
    }

    public boolean updateStaff(int id, UpdateStaffReq updateStaffReq) {
        return staffService.updateStaff(id, updateStaffReq);
    }

    public Staff getStaff(int id) {
        return staffService.getStaff(id);
    }
    public Staff getStaff(String name) {
        return staffService.getStaff(name);
    }
}
