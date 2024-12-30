package view;

import controller.StaffController;
import model.Staff;
import model.dto.CreateStaffReq;
import model.dto.UpdateStaffReq;
import utilies.TableUIConfig;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StaffMenu {
    private static final StaffController staffController = new StaffController();

    public static void listStaff() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Display All Staff's Information ***
                """);
        List<Staff> staffs = staffController.getAllStaff();
        if (!staffs.isEmpty()) {
            TableUIConfig.displayStaff(staffs);
        } else {
            System.out.println("[!] Staff list is empty");
        }
    }

    public static void addNewStaff() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Add New Staff ***
                """);

        String name;
        while (true) {
            System.out.print("[~] Enter Staff's Name (-1: back): ");
            name = new Scanner(System.in).nextLine();
            if (name.equals("-1")) {
                return;
            }
            if (isTextAllLetterValid(name)) {
                break;
            }
            System.out.println("[!] Invalid Name Input!");
        }

        Date dateOfBirth;
        String dob;
        //DOB
        while (true) {
            System.out.print("[~] Enter Staff's Date of Birth (yyyy-MM-dd): ");
            dob = new Scanner(System.in).nextLine();
            try {
                dateOfBirth = Date.valueOf(dob);
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Date Input!");
            }
        }
        String regex = "^[MFmf]$";
        String gender;
        //Gender
        while (true) {
            System.out.print("[~] Gender(M/F): ");
            gender = new Scanner(System.in).nextLine();
            if (Pattern.matches(regex, gender)) {
                break;
            } else {
                System.out.println("[!] Invalid Gender Input!");
            }
        }
        String position;
        while (true) {
            System.out.print("[~] Enter Staff's Position: ");
            position = new Scanner(System.in).nextLine();
            if (isTextAllLetterValid(position)) {
                break;
            }
            System.out.println("[!] Invalid Position Input!");

        }

        CreateStaffReq createStaffReq = new CreateStaffReq(name, dateOfBirth, gender, position);


        boolean isAdd = staffController.addStaff(createStaffReq);
        if (isAdd) {
            System.out.println("[+] Staff added successfully!");
        } else {
            System.out.println("[!] Error Adding Staff!");
        }
    }

    public static void updateStaff() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Update Staff's Information ***
                """);
        int id;
        while (true) {
            try {
                System.out.print("[~] Input Staff's ID (-1: back): ");
                id = new Scanner(System.in).nextInt();
                if (id == -1){
                    return;
                }
                if (id < 0) {
                    System.out.println("[!] Invalid ID Input Cannot be Negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID Input!");
            }
        }

        Staff isFoundStaff = staffController.getStaff(id);

        if (isFoundStaff != null) {
            TableUIConfig.displayStaff(List.of(isFoundStaff));

            System.out.println("[!] Leave Blank if you don't want to Update the Field!!");
            String name;
            while (true) {
                System.out.print("[~] Enter Staff's Name (-1: back): ");
                name = new Scanner(System.in).nextLine();
                if (name.equals("-1")) {
                    return;
                }
                if (!name.isEmpty()) {
                    if (isTextAllLetterValid(name)) {
                        break;
                    }
                    System.out.println("[!] Invalid Name Input!");
                } else break;
            }

            Date dateOfBirth = null;
            String dob;
            //DOB
            while (true) {
                System.out.print("[~] Enter Staff's Date of Birth (yyyy/MM/dd): ");
                dob = new Scanner(System.in).nextLine();
                if (!dob.isEmpty()) {
                    try {
                        dateOfBirth = Date.valueOf(dob);
                        break;
                    } catch (Exception e) {
                        System.out.println("[!] Invalid Date Input!");
                    }
                } else break;
            }
            String regex = "^[MFmf]$";
            String gender;
            //Gender
            while (true) {
                System.out.print("[~] Gender (M/F): ");
                gender = new Scanner(System.in).nextLine();
                if (!gender.isEmpty()) {
                    if (Pattern.matches(regex, gender)) {
                        break;
                    } else {
                        System.out.println("[!] Invalid Gender Input!");
                    }
                } else break;
            }

            String position;
            while (true) {
                System.out.print("[~] Enter Staff's Position: ");
                position = new Scanner(System.in).nextLine();
                if (!position.isEmpty()) {
                    if (isTextAllLetterValid(position)) {
                        break;
                    }
                    System.out.println("[!] Invalid Position Input!");
                } else break;
            }
            Boolean isWorking = null;
            BreakStatusLoop:
            while (true) {
                System.out.println("[~] Staff's Status: ");
                System.out.print("""
                    1. Working
                    2. Resign
                    """);
                System.out.print("[~] Status: ");
                String status = new Scanner(System.in).nextLine();
                if (!status.isEmpty()) {
                    switch (status) {
                        case "1" -> {
                            isWorking = true;
                            break BreakStatusLoop;
                        }
                        case "2" -> {
                            isWorking = false;
                            break BreakStatusLoop;
                        }
                        default -> System.out.println("[!] Invalid Status Input!");
                    }
                } else break;
            }

            UpdateStaffReq updateStaffReq = new UpdateStaffReq(name, gender, dateOfBirth, position, isWorking);


            boolean isAdd = staffController.updateStaff(id, updateStaffReq);
            if (isAdd) {
                System.out.println("[+] Staff Updated successfully!");
            } else {
                System.out.println("[!] Error Updating Staff!");
            }
        }


    }

    public static void deleteStaff() {
        int id;
        System.out.println("""
        *** Delete Staff ***
        """);

        while (true) {
            try {
                System.out.print("[~] Enter Staff's ID (-1: back): ");
                id = new Scanner(System.in).nextInt();
                if (id == -1) {
                    return;
                }
                if (id < 0) {
                    System.out.println("[!] ID cannot be negative!");
                }

                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID!");
            }
        }
        boolean isDelete = staffController.deleteStaff(id);
        if (isDelete) {
            System.out.println("[+] Customer deleted successfully!");
        } else {
            System.out.println("[!] Error Deleting Customer!");
        }
    }

    public static void searchStaff() {
        int id;
        String name;
        int op;
        System.out.println("""
                *** Search Staff *** \n
                1. By ID
                2. By Name
                3. Exit
                """);


        while (true) {
            try {
                System.out.print("[~] Option << ");
                op = new Scanner(System.in).nextInt();
                if (op < 1 || op > 3) {
                    System.out.println("[!] Option Out of Range!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Option!");
            }
        }

        switch (op) {
            case 1 -> {
                System.out.print("[~] Enter Staff's ID (-1: back): ");
                while (true) {
                    try {
                        id = new Scanner(System.in).nextInt();
                        if (id == -1){
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
                Staff foundStaff = staffController.getAllStaff()
                        .stream()
                        .filter(staff -> staff.getId() == finalId)
                        .findFirst()
                        .orElse(null);
                if (foundStaff != null) {
                    TableUIConfig.displayStaff(List.of(foundStaff));
                } else {
                    System.out.println("[!] Staff's not found!");
                }
            }
            case 2 -> {
                System.out.print("[~] Enter Staff's Name (-1: back): ");
                name = new Scanner(System.in).nextLine();
if (name.equals("-1")) {
    return;
}
                Staff foundStaff = staffController.getAllStaff()
                        .stream()
                        .filter(staff -> staff.getStaff_name().equalsIgnoreCase(name))
                        .findFirst()
                        .orElse(null);
                if (foundStaff != null) {
                    TableUIConfig.displayStaff(List.of(foundStaff));
                } else {
                    System.out.println("[!] Staff's not found!");
                }
            }
        }
    }

    static boolean isTextAllLetterValid(String text) {
        String regex = "^[A-Za-z]+([ '-][A-Za-z]+)*$";
        return Pattern.matches(regex, text);
    }
}
