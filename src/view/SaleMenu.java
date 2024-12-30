package view;

import controller.CustomerController;
import controller.ProductController;
import controller.SaleController;
import controller.SaleDetailController;
import model.Customer;
import model.Product;
import model.dto.*;
import utilies.TableUIConfig;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SaleMenu {

    private static final SaleController saleController = new SaleController();
    private static final SaleDetailController saleDetailController = new SaleDetailController();
    private static final CustomerController customerController = new CustomerController();
    private static final ProductController productController = new ProductController();

    public static void listSale() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Display Sales ***
                """);
        List<SaleResponseDto> sales = saleController.getSales();
        if (!sales.isEmpty()) {
            TableUIConfig.displaySale(sales);
            System.out.println("Press Any key to continue...");
            new Scanner(System.in).nextLine();
        } else {
            System.out.println("[!] Sale list is empty");
        }
    }

    public static void displayAsInvoice() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Invoice ***
                """);
        int saleID;
        while (true) {
            System.out.print("[~] Enter Sale's ID (-1: back): ");
            try {
                saleID = new Scanner(System.in).nextInt();
                if (saleID == -1) {
                    return;
                }
                if (saleID <= 0) {
                    System.out.println("[!] ID cannot be negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Sale ID");
            }
        }
        if (saleController.getSale(saleID) != null) {

            TableUIConfig.displaySaleAsInvoice(
                    saleController.getSale(saleID),
                    saleDetailController.getSaleDetailById(saleID)
            );
            System.out.println("Press Any key to continue...");
            new Scanner(System.in).nextLine();
        } else {
            System.out.println("[!] No Sale found with ID: " + saleID + "!");
        }
    }

    public static void addSale() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Add New Sale ***
                """);
        String customerId;
        int id = 0;
        Customer isFoundCustomer = new Customer();
        inputUser:
        while (true) {
            System.out.print("[~] Enter Customer's ID (-1: back): ");
            customerId = new Scanner(System.in).nextLine();
            if (customerId.equals("-1")) {
                return;
            }
            if (customerId.isEmpty()) {
                break;
            } else {
                try {
                    id = Integer.parseInt(customerId);
                    isFoundCustomer = customerController.getCustomerByID(id);
                    if (isFoundCustomer == null) {
                        System.out.println("[!] No Customer found with ID: " + customerId + "!");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("[!] Invalid Customer ID");
                }
            }
        }
        List<CreateSaleDetailReq> createSaleDetailReqs = new ArrayList<>();

        if (isFoundCustomer.getId() == 0) {
            System.out.println("[~] Customer: General Customer");
        } else {
            System.out.println("[~] Customer: " + isFoundCustomer.getCustomer_name());
        }
        int productID = 0;
        Product isFoundProduct;

        CompleteSaleLoop:
        while (true) {
            while (true) {
                try {
                    System.out.print("[~] Enter Product's ID (or -1 to Stop): ");
                    productID = new Scanner(System.in).nextInt();
                    if (productID == -1) {
                        break CompleteSaleLoop;
                    }
                    if (productID <= 0) {
                        System.out.println("[!] Product ID must be positive.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("[!] Invalid Product ID");
                }

                isFoundProduct = productController.getProduct(productID);
                if (isFoundProduct == null) {
                    System.out.println("[!] Product not found. Please try again.");
                } else {
                    break;
                }
            }
            double price;
            while (true) {
                System.out.print("[~] Enter Price: ");
                try {
                    price = new Scanner(System.in).nextDouble();
                    if (price < 0) {
                        System.out.println("[!] Price cannot be negative!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[!] Invalid Price.");
                }

            }
            int quantity;
            while (true) {
                System.out.print("[~] Enter Quantity: ");
                try {
                    quantity = new Scanner(System.in).nextInt();
                    if (quantity <= 0) {
                        System.out.println("[!] Quantity cannot be negative!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[!] Invalid Quantity.");
                }
            }
            double discountPer = 0.0;
            while (true) {
                System.out.print("[~] Enter Discount(%): ");
                try {
                    String discount = new Scanner(System.in).nextLine();
                    if (!discount.isEmpty()) {
                        discountPer = Double.parseDouble(discount);
                        break;
                    } else break;
                } catch (NumberFormatException e) {
                    System.out.println("[!] Invalid Discount.");
                }
            }

            createSaleDetailReqs.add(new CreateSaleDetailReq(
                    0, productID, price, quantity, discountPer
            ));
        }

        if (!createSaleDetailReqs.isEmpty()) {
            int saleID = saleController.addSale(new CreateSaleReq(Login.staffKeyLogin, id));
            for (CreateSaleDetailReq createSaleDetailReq : createSaleDetailReqs) {
                saleDetailController.addSaleDetail(
                        new CreateSaleDetailReq(
                                saleID,
                                createSaleDetailReq.product_id(),
                                createSaleDetailReq.unit_price(),
                                createSaleDetailReq.sale_quantity(),
                                createSaleDetailReq.discount_percent())
                );
            }
            System.out.println("[+] Sale added successfully!");
            System.out.println("[~] Invoice");
            TableUIConfig.displaySaleAsInvoice(
                    saleController.getSale(saleID),
                    saleDetailController.getSaleDetailById(saleID)
            );
            System.out.println("Press Any key to continue...");
            new Scanner(System.in).nextLine();
        } else {
            System.out.println("[!] No Products Added! Error Adding Sale!");
        }
    }

    public static void deleteSale() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Delete Sale ***
                """);
        int saleID;
        while (true) {
            System.out.print("[~] Enter Sale's ID (-1: back): ");
            try {
                saleID = new Scanner(System.in).nextInt();
                if (saleID == -1) {
                    return;
                }
                if (saleID <= 0) {
                    System.out.println("[!] ID cannot be negative!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid Sale ID");
            }
        }
        String choice;
        if (saleController.getSale(saleID) != null) {
            while (true) {
                System.out.println("[?] Do you really want to delete Sale? (Y/N)");
                System.out.print("[~] Option << ");
                choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    if (saleController.deleteSale(saleID)) {
                        System.out.println("[+] Sale successfully deleted!");
                        break;
                    } else System.out.println("[!] Error deleting sale!");
                } else break;
            }
        } else {
            System.out.println("[!] No Sale found with ID: " + saleID + "!");
        }
    }

    public static void searchSale() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Search Sale ***
                """);
        int saleID;
        while (true) {
            System.out.print("[~] Enter Sale's ID (-1: back): ");
            try {
                saleID = new Scanner(System.in).nextInt();
                if (saleID == -1) {
                    return;
                }
                if (saleID <= 0) {
                    System.out.println("[!] ID cannot be negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Sale ID");
            }
        }
        SaleResponseDto foundSale = saleController.getSale(saleID);
        if (foundSale != null) {
            TableUIConfig.displaySale(List.of(foundSale));
            System.out.println("Press Any key to continue...");
            new Scanner(System.in).nextLine();
        } else {
            System.out.println("[!] No Sale found with ID: " + saleID + "!");
        }

    }

    public static void voidSale() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Void Sale ***
                """);
        int saleID;
        while (true) {
            System.out.print("[~] Enter Sale's ID (-1: back): ");
            try {
                saleID = new Scanner(System.in).nextInt();
                if (saleID == -1) {
                    return;
                }
                if (saleID <= 0) {
                    System.out.println("[!] ID cannot be negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Sale ID");
            }
        }

        SaleResponseDto foundSale = saleController.getSale(saleID);

        if (foundSale != null) {
            TableUIConfig.displaySale(List.of(foundSale));
            if (!foundSale.status().equals("VOIDED")) {
                System.out.print("[~] Input Void Reason: ");
                String reason = new Scanner(System.in).nextLine();
                boolean isVoided = saleController.voidSale(saleID, new UpdateVoidSaleReq(reason));
                if (isVoided) {
                    System.out.println("[+] Void Sale successfully updated!");
                } else System.out.println("[!] Error voiding sale!");
            } else System.out.println("[!] Sale is already voided!");
        } else {
            System.out.println("[!] No Sale found with ID: " + saleID + "!");
        }
    }

    public static void saleReport() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Sale Report ***
                """);
        System.out.println("=".repeat(25));
        System.out.println("[!] Date Format yyyy/MM/dd (-1: back))");
        System.out.println("=".repeat(25));
        System.out.print("[~] Enter Start Date: ");
        String startDate = new Scanner(System.in).nextLine();
        if (startDate.equals("-1")) {
            return;
        }
        System.out.print("[~] Enter End Date: ");
        String endDate = new Scanner(System.in).nextLine();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate sDate = LocalDate.parse(startDate, format);
            Date startDateSql = Date.valueOf(sDate);
            LocalDate eDate = LocalDate.parse(endDate, format);
            Date endDateSql = Date.valueOf(eDate);
            List<SaleReportResponseDto> saleReportResponseDto = saleController.getSaleReport(startDateSql, endDateSql);
            if (saleReportResponseDto != null) {
                TableUIConfig.displaySaleReport(saleReportResponseDto);
                double totalSale = saleReportResponseDto.stream().mapToDouble(SaleReportResponseDto::totalEarn).sum();
                int totalProductSold = saleReportResponseDto.stream().mapToInt(SaleReportResponseDto::totalProductsSold).sum();
                System.out.println("=".repeat(25));
                System.out.println("[~] From Date: " + sDate.format(displayFormat));
                System.out.println("[~] To Date: " + eDate.format(displayFormat));
                System.out.println("[~] Total Product Sold: " + totalProductSold);
                System.out.println("[~] Total Earned: $ " + totalSale);
            } else System.out.println("[!] No Sale Report found!");
            System.out.println("Press Any key to continue...");
            new Scanner(System.in).nextLine();
        } catch (Exception e) {
            System.out.println("[!] Incorrect Date Input!");
        }
    }
}