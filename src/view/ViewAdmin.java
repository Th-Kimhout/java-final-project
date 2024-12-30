package view;

import java.util.Scanner;


public class ViewAdmin {
    public static void viewAdmin() {

        System.out.println("""
                
                                                             __        __   _                            _          ____   ___  ____ \s
                                                             \\ \\      / /__| | ___ ___  _ __ ___   ___  | |_ ___   |  _ \\ / _ \\/ ___|\s
                                                              \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | |_) | | | \\___ \\\s
                                                               \\ V  V /  __/ | (_| (_) | | | | | |  __/ | || (_) | |  __/| |_| |___) |
                                                                \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  |_|    \\___/|____/\s
                                                                                                                                     \s
                
                """);
        while (true) {
            System.out.println();
            System.out.println("=".repeat(25));
            System.out.println("""
                    *** ADMIN Menus *** \n
                    1. Product
                    2. Category
                    3. Customer
                    4. Sale
                    5. Staff
                    6. User
                    7. Log Out
                    8. Exit
                    """);

            System.out.print("[~] Option << ");
            int op = new Scanner(System.in).nextInt();
            switch (op) {
                case 1 -> {
                    BreakFromProductMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Manage Product *** \n
                                1. List Products
                                2. Add Product
                                3. Update Product
                                4. Delete Product
                                5. Search Product
                                6. Exit
                                """);

                        int choice;

                        while (true) {
                            System.out.print("[~] Option << ");
                            try {
                                choice = new Scanner(System.in).nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("[!] Invalid input");
                            }
                        }

                        switch (choice) {
                            case 1 -> ProductMenu.listProducts();

                            //Add Product
                            case 2 -> ProductMenu.addNewProduct();

                            //Update Product
                            case 3 -> ProductMenu.updateProduct();

                            //Delete Product
                            case 4 -> ProductMenu.deleteProduct();

                            //Search Product
                            case 5 -> ProductMenu.searchProduct();

                            case 6 -> {
                                break BreakFromProductMenu;
                            }
                            default -> System.out.println("[!] Invalid Input!");
                        }
                    }

                }
                case 2 -> {
                    BreakFromCategoryMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Manage Category *** \n
                                1. List Category
                                2. Add Category
                                3. Update Category
                                4. Delete Category
                                5. Search Category
                                6. Exit
                                """);

                        int choice;

                        while (true) {
                            System.out.print("[~] Option << ");
                            try {
                                choice = new Scanner(System.in).nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("[!] Invalid input!");
                            }
                        }

                        switch (choice) {
                            case 1 -> CategoryMenu.listCategories();

                            //Add Category
                            case 2 -> CategoryMenu.addCategory();

                            //Update Category
                            case 3 -> CategoryMenu.updateCategory();

                            //Delete Category
                            case 4 -> CategoryMenu.deleteCategory();

                            //Search Category
                            case 5 -> CategoryMenu.searchCategory();

                            case 6 -> {
                                break BreakFromCategoryMenu;
                            }
                            default -> System.out.println("[!] Invalid input!");
                        }
                    }

                }
                case 3 -> {
                    BreakFromCustomerMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Manage Customer *** \n
                                1. List Customer
                                2. Add Customer
                                3. Update Customer
                                4. Delete Customer
                                5. Search Customer
                                6. Exit
                                """);

                        int choice;

                        while (true) {
                            System.out.print("[~] Option << ");
                            try {
                                choice = new Scanner(System.in).nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("[!] Invalid input");
                            }
                        }

                        switch (choice) {
                            case 1 -> CustomerMenu.listCustomer();

                            //Add Customer
                            case 2 -> CustomerMenu.addNewCustomer();

                            //Update Customer
                            case 3 -> CustomerMenu.updateCustomer();

                            //Delete Customer
                            case 4 -> CustomerMenu.deleteCustomer();

                            //Search Customer
                            case 5 -> CustomerMenu.searchCustomer();

                            case 6 -> {
                                break BreakFromCustomerMenu;
                            }
                            default -> System.out.println("[!] Invalid input!");
                        }
                    }
                }
                case 4 -> {
                    BreakFromSaleMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Manage Sale *** \n
                                1. List Sale
                                2. Add Sale
                                3. Void Sale
                                4. Delete Sale
                                5. Search Sale
                                6. Show Sale as Invoice
                                7. Sale Report by Date
                                8. Exit
                                """);

                        int choice;

                        while (true) {
                            System.out.print("[~] Option << ");
                            try {
                                choice = new Scanner(System.in).nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("[!] Invalid input!");
                            }
                        }

                        switch (choice) {
                            case 1 -> SaleMenu.listSale();

                            //Add Sale
                            case 2 -> SaleMenu.addSale();

                            //Update Sale
                            case 3 -> SaleMenu.voidSale();

                            //Delete Sale
                            case 4 -> SaleMenu.deleteSale();

                            //Search Sale
                            case 5 -> SaleMenu.searchSale();

                            case 6 -> SaleMenu.displayAsInvoice();

                            case 7 -> SaleMenu.saleReport();

                            case 8 -> {
                                break BreakFromSaleMenu;
                            }
                            default -> System.out.println("[!] Invalid input!");
                        }
                    }
                }
                case 5 -> {
                    BreakFromStaffMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Manage Staff *** \n
                                1. List Staff
                                2. Add Staff
                                3. Update Staff
                                4. Delete Staff
                                5. Search Staff
                                6. Exit
                                """);

                        int choice;

                        while (true) {
                            System.out.print("[~] Option << ");
                            try {
                                choice = new Scanner(System.in).nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("[!] Invalid input!");
                            }
                        }

                        switch (choice) {
                            case 1 -> StaffMenu.listStaff();

                            //Add Staff
                            case 2 -> StaffMenu.addNewStaff();

                            //Update Staff
                            case 3 -> StaffMenu.updateStaff();

                            //Delete Staff
                            case 4 -> StaffMenu.deleteStaff();

                            //Search Staff
                            case 5 -> StaffMenu.searchStaff();

                            case 6 -> {
                                break BreakFromStaffMenu;
                            }
                            default -> System.out.println("[!] Invalid input");
                        }
                    }
                }
                case 6 -> {
                    BreakFromUserMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Manage User *** \n
                                1. List User
                                2. Add User
                                3. Update User
                                4. Delete User
                                5. Search User
                                6. Exit
                                """);

                        int choice;

                        while (true) {
                            System.out.print("[~] Option << ");
                            try {
                                choice = new Scanner(System.in).nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("[!] Invalid Input!");
                            }
                        }

                        switch (choice) {
                            case 1 -> UserMenu.listAllUsers();

                            //Add User
                            case 2 -> UserMenu.addUser();

                            //Update User
                            case 3 -> UserMenu.updateUser();

                            //Delete User
                            case 4 -> UserMenu.deleteUser();

                            //Search User
                            case 5 -> UserMenu.searchUser();

                            case 6 -> {
                                break BreakFromUserMenu;
                            }
                            default -> System.out.println("[!] Invalid Input!");
                        }
                    }
                }

                case 7 -> Login.login();

                case 8 -> System.exit(0);
                default -> System.out.println("[!] Invalid option!");
            }
        }
    }

}
