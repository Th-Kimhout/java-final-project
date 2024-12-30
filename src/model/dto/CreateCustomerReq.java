package model.dto;


import java.sql.Date;

public record CreateCustomerReq(String customer_name, Date customer_dob, String customer_gender) {
}
