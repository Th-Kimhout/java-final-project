package model.dto;


import java.sql.Date;

public record UpdateStaffReq(String staffName, String staffGender, Date staffDOB, String staffPosition,
                             Boolean isWorking) {

}
