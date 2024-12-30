package model.dto;

import lombok.Data;

import java.sql.Date;

public record UpdateCustomerReq (String customer_name, Date customer_dob, String customer_gender) {

}
