package service;

import model.User;
import model.dto.CreateUserReq;
import model.dto.UpdateUserReq;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    boolean addUser(CreateUserReq createUserReq);
    boolean updateUser(int id, UpdateUserReq updateUserReq);
    boolean deleteUser(int id);
    User getUserByID(int id);
    User getUserByUsername(String name);
    User userLogin(String username, String password, String role);

}
