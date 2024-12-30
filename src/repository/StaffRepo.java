package repository;

import model.Staff;
import model.dto.CreateStaffReq;
import model.dto.UpdateStaffReq;

import java.util.List;

public interface StaffRepo {
    List<Staff> getAllStaffs();
    boolean addStaff(CreateStaffReq createStaff);
    boolean updateStaff(int id, UpdateStaffReq updateStaff);
    boolean deleteStaff(int id);

}
