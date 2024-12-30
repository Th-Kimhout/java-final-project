package model.dto;

import java.sql.Date;

public record CreateStaffReq(String staffName, Date staff_dob, String staff_gender, String staff_position) {
}
