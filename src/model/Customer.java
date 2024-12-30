package model;

import lombok.Data;

import java.sql.Date;

@Data

public class Customer {
    private int id;
    private String customer_name;
    private Date customer_dob;
    private String customer_gender;

    public Customer() {}

    public Customer(int id, String customer_name, Date customer_dob, String customer_gender) {
        this.id = id;
        this.customer_name = customer_name;
        this.customer_dob = customer_dob;
        this.customer_gender = customer_gender;
    }
}
