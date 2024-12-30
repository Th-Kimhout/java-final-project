package model;

import lombok.Data;

@Data

public class User {
    private int userID;
    private int staffID;
    private String userName;
    private String password;
    private String role;
    private Boolean status;

    public User(){}

    public User(int userID, int staffID, String userName, String password, String role, Boolean status) {
        this.userID = userID;
        this.staffID = staffID;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
