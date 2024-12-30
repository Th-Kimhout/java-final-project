package view;

import controller.UserController;

import model.User;
import model.dto.CreateUserReq;
import model.dto.UpdateUserReq;
import model.dto.UserResponseDto;

import utilies.TableUIConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserMenu {
    private static final UserController userController = new UserController();

    public static void listAllUsers() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Display User's Information ***
                """);
        List<User> users = userController.getAllUser();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            userResponseDtos.add(new UserResponseDto(user.getUserID(), user.getStaffID(), user.getUserName(), user.getRole(), user.getStatus()));
        }
        if (!users.isEmpty()) {
            TableUIConfig.displayUser(userResponseDtos);
        } else {
            System.out.println("[!] Customer list is empty");
        }
    }

    public static void addUser() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Add New User ***
                """);
        int staffId;
        while (true) {
            System.out.print("Enter StaffID (-1: back): ");
            try {
                staffId = new Scanner(System.in).nextInt();
                if (staffId == -1) {
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid StaffID!");
            }
        }
        System.out.print("[~] Enter Username: ");
        String username = new Scanner(System.in).nextLine();

        String userRole;
        System.out.println("[~] Enter Role: ");
        System.out.print("""
                1. ADMIN
                2. GENERAL USER
                """);
        whileLoop:
        while (true) {
            try {
                System.out.println("[~] Option << ");
                int role = new Scanner(System.in).nextInt();
                switch (role) {
                    case 1:
                        userRole = "ADMIN";
                        break whileLoop;
                    case 2:
                        userRole = "GENERAL USER";
                        break whileLoop;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }


        String password;
        while (true) {
            System.out.print("[~] Enter Password: ");
            password = new Scanner(System.in).nextLine();
            System.out.print("[~] Enter Confirm Password: ");
            String confirmPassword = new Scanner(System.in).nextLine();
            if (password.equals(confirmPassword)) {
                break;
            } else System.out.println("[!] Password is not matched!");
        }
        if (password.isEmpty() || username.isEmpty()) {
            System.out.println("[!] All Fields Are Required!");
            return;
        }


        boolean idAdded = userController.addUser(new CreateUserReq(staffId, username, password, userRole));
        if (idAdded) {
            System.out.println("[!] User Added Successfully!");
        } else System.out.println("[!] Error Adding User!");

    }

    public static void deleteUser() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Delete User ***
                """);
        int id;
        while (true) {
            System.out.print("[~] Enter User ID (-1: back): ");
            try {
                id = new Scanner(System.in).nextInt();
                if (id == -1) {
                    return;
                }
                if (id <= 0) {
                    System.out.println("[!] Invalid ID cannot be Negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID");
            }
        }

        int finalId = id;
        boolean foundUser = userController.getAllUser().stream().anyMatch(user -> user.getUserID() == finalId);
        String choice = "";
        if (foundUser) {
            while (true) {
                try {
                    System.out.println("Do you really want to delete this user? (Y/N)");
                    System.out.print("[~] Option << ");
                    choice = new Scanner(System.in).nextLine();
                } catch (Exception e) {
                    System.out.println("[!] Invalid Choice!");
                }
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    boolean isDelete = userController.deleteUser(finalId);
                    if (isDelete) {
                        System.out.println("[+] User deleted successfully!");
                        break;
                    } else {
                        System.out.println("[!]Error Deleting User!");
                    }
                } else break;
            }
        }
    }

    public static void updateUser() {
        System.out.println("""
                *** Update User ***
                """);
        int id;
        while (true) {
            System.out.print("[~] Enter User ID (-1: back): ");
            try {
                id = new Scanner(System.in).nextInt();
                if (id == -1) {
                    return;
                }
                if (id < 0) {
                    System.out.println("[!] Invalid ID cannot be Negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID");
            }
        }
        User foundUser = userController.findUserById(id);
        if (foundUser != null) {
            TableUIConfig.displayUser(List.of(new UserResponseDto(foundUser.getUserID(), foundUser.getStaffID(), foundUser.getUserName(), foundUser.getRole(), foundUser.getStatus())));
            System.out.println("[!] Leave the field empty if you dun want to update.");
            System.out.print("[~] Enter New Username: ");
            String newUsername = new Scanner(System.in).nextLine();
            String newPassword;
            while (true) {
                System.out.print("[~] Enter New Password: ");
                newPassword = new Scanner(System.in).nextLine();
                System.out.print("[~] Confirm New Password: ");
                String cfPassword = new Scanner(System.in).nextLine();
                if (newPassword.equals(cfPassword)) {
                    break;
                } else System.out.println("[!] Passwords do not match!");
            }
            String userRole = "";
            System.out.println("[~] Enter Role: ");
            System.out.print("""
                    1. ADMIN
                    2. GENERAL USER
                    """);
            whileLoop:
            while (true) {
                try {
                    System.out.print("[~] Option << ");
                    String role = new Scanner(System.in).nextLine();
                    switch (role) {
                        case "1":
                            userRole = "ADMIN";
                            break whileLoop;
                        case "2":
                            userRole = "GENERAL USER";
                            break whileLoop;
                        case "":
                            break whileLoop;
                        default:
                            System.out.println("Invalid Input");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            }

            System.out.println("[~] Enter Status: ");
            System.out.print("""
                    1. IsWorking
                    2. Inactive
                    """);
            Boolean status = null;
            whileLoop:
            while (true) {

                try {
                    System.out.print("[~] Option << ");
                    String opt = new Scanner(System.in).nextLine();
                    switch (opt) {
                        case "1":
                            status = true;
                            break whileLoop;
                        case "2":
                            status = false;
                            break whileLoop;
                        case "":
                            break whileLoop;
                        default:
                            System.out.println("[!] Invalid Input!");
                    }
                } catch (Exception e) {
                    System.out.println("[!] Invalid Input!");
                }
            }
            boolean isUpdated = userController.updateUser(id, new UpdateUserReq(newUsername, newPassword, userRole, status));
            if (isUpdated) {
                System.out.println("[!] User Updated Successfully!");
            } else System.out.println("[!] Error Updating User!");
        }else {
            System.out.println("[!] User Not Found!");
        }
    }

    public static void searchUser() {
        int id;
        String name;
        int op;
        System.out.println("""
                *** Search User *** \n
                1. By ID
                2. By Username
                3. Exit
                """);


        while (true) {
            try {
                System.out.print("[~] Option << ");
                op = new Scanner(System.in).nextInt();
                if (op < 1 || op > 3) {
                    System.out.println("[!] Invalid Option!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Input!");
            }
        }

        switch (op) {
            case 1 -> {

                while (true) {
                    try {
                        System.out.print("[~] Enter User's ID (-1: back): ");
                        id = new Scanner(System.in).nextInt();
                        if (id == -1) {
                            return;
                        }
                        if (id < 0) {
                            System.out.println("[!] ID cannot be negative!");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("[!] Invalid ID!");
                    }
                }

                int finalId = id;
                User foundUser = userController.getAllUser().stream().filter(user -> user.getUserID() == finalId).findFirst().orElse(null);

                if (foundUser != null) {
                    UserResponseDto userResponseDto = new UserResponseDto(foundUser.getUserID(), foundUser.getStaffID(), foundUser.getUserName(), foundUser.getRole(), foundUser.getStatus());
                    TableUIConfig.displayUser(List.of(userResponseDto));
                } else {
                    System.out.println("[!] User's not found!");
                }
            }
            case 2 -> {
                System.out.print("[~] Enter Username (-1: back): ");
                name = new Scanner(System.in).nextLine();
                if (name.equals("-1")) {
                    return;
                }
                User foundUser = userController.getAllUser().stream().filter(user -> user.getUserName().equals(name)).findFirst().orElse(null);
                if (foundUser != null) {
                    UserResponseDto userResponseDto = new UserResponseDto(foundUser.getUserID(), foundUser.getStaffID(), foundUser.getUserName(), foundUser.getRole(), foundUser.getStatus());

                    TableUIConfig.displayUser(List.of(userResponseDto));
                } else {
                    System.out.println("[!] Customer's not found!");
                }
            }
        }
    }
}
