package controller;

import model.User;
import model.dto.CreateUserReq;
import model.dto.UpdateUserReq;
import repository.UserRepoImpl;
import service.UserServiceImpl;

import java.util.List;

public class UserController {
    UserServiceImpl userService = new UserServiceImpl();


    public User login(String username, String password, String role) {
        return userService.userLogin(username, password, role);
    }

    public List<User> getAllUser(){
        return userService.getUsers();
    }

    public boolean addUser(CreateUserReq createUserReq) {
        return userService.addUser(createUserReq);
    }

    public boolean updateUser(int id, UpdateUserReq updateUserReq) {
        return userService.updateUser(id, updateUserReq);
    }

    public boolean deleteUser(int id) {
        return userService.deleteUser(id);
    }

    public User findUserById(int id) {
        return userService.getUserByID(id);
    }

    public User findUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}
