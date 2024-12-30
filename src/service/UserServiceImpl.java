package service;

import model.User;
import model.dto.CreateUserReq;
import model.dto.UpdateUserReq;
import repository.UserRepoImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepoImpl userRepo = new UserRepoImpl();

    @Override
    public List<User> getUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public boolean addUser(CreateUserReq createUserReq) {
        return userRepo.addUser(createUserReq);
    }

    @Override


    public boolean updateUser(int id, UpdateUserReq updateUserReq) {

        if (!(updateUserReq.username().isEmpty()
                && updateUserReq.role().isEmpty()
                && updateUserReq.password().isEmpty()
                && updateUserReq.status() == null)) {
            if (userRepo.getAllUsers().stream().anyMatch(user -> user.getUserID() == id)) {
                return userRepo.updateUser(id, updateUserReq);
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if (userRepo.getAllUsers().stream().anyMatch(user -> user.getUserID() == id)) {
            return userRepo.deleteUser(id);
        }
        return false;
    }

    @Override
    public User getUserByID(int id) {
        return userRepo.getAllUsers()
                .stream()
                .filter(user -> user.getUserID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepo.getAllUsers()
                .stream()
                .filter(user -> user.getUserName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User userLogin(String username, String password, String role) {
        return userRepo.getAllUsers()
                .stream()
                .filter(
                        user -> user.getUserName().equals(username) &&
                                user.getPassword().equals(password) &&
                                user.getRole().equals(role))
                .findFirst().orElse(null);
    }
}
