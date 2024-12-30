package model;

import lombok.Data;

import java.sql.Date;

@Data

public class Sale {
    private int id;
    private Date create_date;
    private int staff_id;
    private int customer_id;
    private double total_amount;
    private String status;
    private String void_reason;
    private Date void_date;

    public Sale() {
    }

    public Sale(int id, Date sale_date, int staff_id, int customer_id, double total_amount, String status, String void_reason, Date void_date) {
        this.id = id;
        this.create_date = sale_date;
        this.staff_id = staff_id;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.status = status;
        this.void_reason = void_reason;
        this.void_date = void_date;
    }
}
