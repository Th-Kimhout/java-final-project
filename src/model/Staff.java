package model;

import lombok.Data;

import java.sql.Date;

@Data

public class Staff {
    private int id;
    private String staff_name;
    private Date staff_dob;
    private String staff_gender;
    private String staff_position;
    private boolean is_working;

    public Staff() {
    }

    public Staff(int id, String staff_name, Date staff_dob, String staff_gender, String staff_position, boolean is_working) {
        this.id = id;
        this.staff_name = staff_name;
        this.staff_dob = staff_dob;
        this.staff_gender = staff_gender;
        this.staff_position = staff_position;
        this.is_working = is_working;
    }

}
