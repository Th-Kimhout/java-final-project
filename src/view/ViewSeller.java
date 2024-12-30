package view;

import java.util.Scanner;

public class ViewSeller {


    public static void viewSeller() {

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
                    *** User Menus *** \n
                    1. Product
                    2. Sale
                    3. Sale Report
                    4. Show Sale as Invoice
                    5. Log Out
                    6. Exit
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
                                *** Product Menu *** \n
                                1. List Products
                                2. Search Product
                                3. Exit
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
                            case 1 -> ProductMenu.listProducts();

                            //Search Product
                            case 2 -> ProductMenu.searchProduct();

                            case 3 -> {
                                break BreakFromProductMenu;
                            }
                            default -> System.out.println("[!] Invalid input!");
                        }
                    }

                }

                case 2 -> {
                    BreakFromSaleMenu:
                    while (true) {
                        System.out.println();
                        System.out.println("=".repeat(25));
                        System.out.println("""
                                *** Sale Menu *** \n
                                1. List Sale
                                2. Add Sale
                                3. Void Sale
                                4. Search Sale
                                5. Display Sale as Invoice
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
                            case 1 -> SaleMenu.listSale();

                            //Add Sale
                            case 2 -> SaleMenu.addSale();

                            //Update Sale
                            case 3 -> SaleMenu.voidSale();

                            //Search Sale
                            case 4 -> SaleMenu.searchSale();

                            case 5 -> SaleMenu.displayAsInvoice();

                            case 6 -> {
                                break BreakFromSaleMenu;
                            }
                            default -> System.out.println("[!] Invalid input!");
                        }
                    }
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("=".repeat(25));
                    SaleMenu.saleReport();
                }

                case 4 -> {
                    System.out.println();
                    System.out.println("=".repeat(25));
                    SaleMenu.displayAsInvoice();
                }

                case 5 -> Login.login();

                case 6 -> System.exit(0);
                default -> System.out.println("[!] Invalid option!");
            }
        }
    }
}
