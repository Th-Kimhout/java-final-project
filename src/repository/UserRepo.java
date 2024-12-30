package repository;

import model.User;
import model.dto.CreateUserReq;
import model.dto.UpdateUserReq;

import java.util.List;

public interface UserRepo {
    List<User> getAllUsers();
    boolean addUser(CreateUserReq createUserReq);
    boolean updateUser(int id, UpdateUserReq updateUserReq);
    boolean deleteUser(int id);

}
