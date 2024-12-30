package view;

import controller.CustomerController;
import model.Customer;
import model.dto.CreateCustomerReq;
import model.dto.UpdateCustomerReq;
import utilies.TableUIConfig;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class CustomerMenu {

    private static final CustomerController customerController = new CustomerController();

    public static void listCustomer() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Display Customer's Information ***
                """);
        List<Customer> customers = customerController.getCustomers();
        if (!customers.isEmpty()) {
            TableUIConfig.DisplayCustomer(customers);
        } else {
            System.out.println("[!] Customer list is empty");
        }
    }

    public static void addNewCustomer() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Add New Customer ***
                """);
        String name;
        while (true) {
            System.out.print("[~] Enter Customer's Name (-1: back): ");
            name = new Scanner(System.in).nextLine();
            if (name.equals("-1")) {
                return;
            }
            if (isNameValid(name)) {
                break;
            } else System.out.println("[!] Invalid Name Input!");
        }

        Date dateOfBirth;
        //DOB
        while (true) {
            System.out.print("[~] Enter Customer's Date of Birth (yyyy-MM-dd): ");
            String dob = new Scanner(System.in).nextLine();
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
            System.out.print("[~] Gender (M or F): ");
            gender = new Scanner(System.in).nextLine();
            if (Pattern.matches(regex, gender)) {
                break;
            } else {
                System.out.println("[!] Invalid Gender Input!");
            }
        }

        if (name.isEmpty() || dateOfBirth == null || gender.isEmpty()) {
            System.out.println("[!] All Fields Are Required!");
        } else {
            CreateCustomerReq createCustomerReq = new CreateCustomerReq(name, dateOfBirth, gender);

            boolean isAdd = customerController.addCustomer(createCustomerReq);
            if (isAdd) {
                System.out.println("[+] Customer added successfully!");
            } else {
                System.out.println("[!] Error Adding Customer!");
            }
        }
    }

    public static void updateCustomer() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Update Customer's Information ***
                """);
        int id;
        while (true) {
            try {
                System.out.print("[~] Input Customer's ID (-1: back): ");
                id = new Scanner(System.in).nextInt();
                if (id == -1) {
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

        if (customerController.getCustomerByID(id) == null) {
            System.out.println("[!] Customer not found!");
        } else {
            TableUIConfig.DisplayCustomer(List.of(customerController.getCustomerByID(id)));
            System.out.println("[!] Leave Blank if you don't want to Update the Field!!");
            String name;
            while (true) {
                System.out.print("[~] Enter Customer's Name: ");
                name = new Scanner(System.in).nextLine();
                if (!name.isEmpty()) {
                    if (isNameValid(name)) {
                        break;
                    } else System.out.println("[!] Invalid Name Input!");
                } else break;
            }

            String dob;
            Date dateOfBirth = null;
            //DOB
            while (true) {
                System.out.print("[~] Enter Customer's Date of Birth (yyyy-MM-dd): ");
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
                System.out.print("[~] Gender(M or F): ");
                gender = new Scanner(System.in).nextLine();
                if (!gender.isEmpty()) {
                    if (Pattern.matches(regex, gender)) {
                        break;
                    } else {
                        System.out.println("[!] Invalid Gender Input!");
                    }
                } else break;
            }

            UpdateCustomerReq updateCustomerReq = new UpdateCustomerReq(name, dateOfBirth, gender);


            boolean isAdd = customerController.updateCustomer(id, updateCustomerReq);
            if (isAdd) {
                System.out.println("[+] Customer Updated successfully!");
                TableUIConfig.DisplayCustomer(List.of(customerController.getCustomerByID(id)));
            } else {
                System.out.println("[!] Error Updating Customer!");
            }
        }

    }

    public static void deleteCustomer() {
        int id;
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Delete Customer ***
                """);

        while (true) {
            try {
                System.out.print("[~] Enter Customer's ID (-1: back): ");
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
        Customer foundCustomer = customerController.getCustomerByID(id);
        if (foundCustomer != null) {
            while (true) {
                String choice;

                System.out.println("[?] Do you really want to Delete the Customer? (Y/N) ");
                System.out.print("[~] Option << ");
                choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    boolean isDelete = customerController.deleteCustomer(id);
                    if (isDelete) {
                        System.out.println("[+] Customer deleted successfully!");
                    } else {
                        System.out.println("[!] Error Deleting Customer!");
                        break;
                    }
                } else break;
            }
        } else {
            System.out.println("[!] Customer not found!");
        }

    }

    public static void searchCustomer() {
        int id;
        String name;
        int op;
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Search Customer *** \n
                1. By ID
                2. By Name
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
                        System.out.print("[~] Enter Customer's ID (-1: back): ");
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
                Customer foundCustomer = customerController.getCustomers()
                        .stream()
                        .filter(customer -> customer.getId() == finalId)
                        .findFirst()
                        .orElse(null);
                if (foundCustomer != null) {
                    TableUIConfig.DisplayCustomer(List.of(foundCustomer));
                } else {
                    System.out.println("[!] Customer's not found!");
                }
            }
            case 2 -> {
                System.out.print("[~] Enter Customer's Name (-1: back): ");
                name = new Scanner(System.in).nextLine();
                if (name.equals("-1")) {
                    return;
                }
                Customer foundCustomer = customerController.getCustomers()
                        .stream()
                        .filter(customer -> customer.getCustomer_name().equalsIgnoreCase(name))
                        .findFirst()
                        .orElse(null);
                if (foundCustomer != null) {
                    TableUIConfig.DisplayCustomer(List.of(foundCustomer));
                } else {
                    System.out.println("[!] Customer's not found!");
                }
            }
        }
    }

    private static boolean isNameValid(String name) {
        String regex = "^[A-Za-z]+([ '-][A-Za-z]+)*$";
        return Pattern.matches(regex, name);
    }
}
