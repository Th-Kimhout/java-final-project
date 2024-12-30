package model.dto;

public record CreateUserReq(int staffID, String username, String password, String role) {
}
