package service;

import model.Staff;
import model.dto.CreateStaffReq;
import model.dto.UpdateStaffReq;

import java.util.List;

public interface StaffService {
    List<Staff> getStaffs();
    boolean addStaff(CreateStaffReq createStaffReq);
    boolean updateStaff(int id, UpdateStaffReq updateStaffReq);
    boolean deleteStaff(int id);
    Staff getStaff(int id);
    Staff getStaff(String staffName);
}
