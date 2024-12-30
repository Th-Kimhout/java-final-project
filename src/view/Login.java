package view;

import controller.UserController;
import model.User;

import java.util.Scanner;

public class Login {

    public static int staffKeyLogin;

    public static void login() {
        UserController userController = new UserController();

        System.out.println("""
                
                         _       __       __                                 __            __  ___ _         _    ____   ____  _____    _____               __                \s
                        | |     / /___   / /_____ ____   ____ ___   ___     / /_ ____     /  |/  /(_)____   (_)  / __ \\ / __ \\/ ___/   / ___/ __  __ _____ / /_ ___   ____ ___\s
                        | | /| / // _ \\ / // ___// __ \\ / __ `__ \\ / _ \\   / __// __ \\   / /|_/ // // __ \\ / /  / /_/ // / / /\\__ \\    \\__ \\ / / / // ___// __// _ \\ / __ `__ \\
                        | |/ |/ //  __// // /__ / /_/ // / / / / //  __/  / /_ / /_/ /  / /  / // // / / // /  / ____// /_/ /___/ /   ___/ // /_/ /(__  )/ /_ /  __// / / / / /
                        |__/|__/ \\___//_/ \\___/ \\____//_/ /_/ /_/ \\___/   \\__/ \\____/  /_/  /_//_//_/ /_//_/  /_/     \\____//____/   /____/ \\__, //____/ \\__/ \\___//_/ /_/ /_/\s
                                                                                                                                           /____/                             \s
                
                """);

        System.out.println("""
                
                                                                             __        ______     _______  __  .__   __.\s
                                                                            |  |      /  __  \\   /  _____||  | |  \\ |  |\s
                                                                            |  |     |  |  |  | |  |  __  |  | |   \\|  |\s
                                                                            |  |     |  |  |  | |  | |_ | |  | |  . `  |\s
                                                                            |  `----.|  `--'  | |  |__| | |  | |  |\\   |\s
                                                                            |_______| \\______/   \\______| |__| |__| \\__|\s
                                                                                                                        \s
                """);
        String username;
        String password;
        String userRole = "";
        System.out.println();
        while (true) {
            System.out.println("=".repeat(25));
            System.out.print("[~] Username: ");
            username = new Scanner(System.in).nextLine();
            System.out.println("=".repeat(25));
            System.out.print("[~] Password: ");
            password = new Scanner(System.in).nextLine();
            System.out.println("=".repeat(25));
            System.out.println("[~] Role: ");
            System.out.print("""
                    1.Admin
                    2.General User
                    """);
            while (true) {
                try {
                    System.out.print("[~] Option << ");
                    int role = new Scanner(System.in).nextInt();
                    switch (role) {
                        case 1:
                            userRole = "ADMIN";
                            break;
                        case 2:
                            userRole = "GENERAL USER";
                            break;
                        default:
                            System.out.println("[!] Invalid Input!");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("[!] Invalid Input!");
                }
                break;
            }

            User foundUser = userController.login(username, password, userRole);
            if (foundUser != null) {
                if (foundUser.getStatus()) {
                    staffKeyLogin = foundUser.getStaffID();
                    if (foundUser.getRole().equals("ADMIN") && foundUser.getStatus()) {
                        ViewAdmin.viewAdmin();
                    } else if (foundUser.getRole().equals("GENERAL USER") && foundUser.getStatus()) {
                        ViewSeller.viewSeller();
                    } else {
                        System.out.println("[!] Username or password or role is incorrect!");
                    }
                }else System.out.println("[!] Account is inactive! Cannot login!");
            } else {
                System.out.println("[!] Username or password or role is incorrect!");
            }
        }
    }
}
