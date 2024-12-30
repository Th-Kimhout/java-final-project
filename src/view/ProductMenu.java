package view;

import controller.CategoryController;
import controller.ProductController;
import model.Category;
import model.Product;
import model.dto.CreateProductReq;
import model.dto.UpdateProductReq;
import utilies.TableUIConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProductMenu {

    private static final ProductController productController = new ProductController();
    private static final CategoryController categoryController = new CategoryController();

    public static void listProducts() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Display Products ***
                """);
        List<Product> products = productController.getProducts();
        if (!products.isEmpty()) {
            TableUIConfig.displayProduct(products);
        } else {
            System.out.println("[!] Product list is empty");
        }
    }

    public static void addNewProduct() {
        int category;
        int quantity;
        double price;

        System.out.println("""
                *** Add Product ***
                """);
        System.out.print("[~] Enter Product's description (-1: back): ");
        String desc = new Scanner(System.in).nextLine();
        if (desc.equals("-1")) {
            return;
        }
        //Category
        while (true) {
            try {
                System.out.println("[~] Enter Product's CategoryID: ");
                for (Category c : categoryController.getCategories()) {
                    System.out.println("ID: " + c.getId() + " - " + c.getCategory_name());
                }
                System.out.print("[~] Option << ");
                category = new Scanner(System.in).nextInt();
                if (categoryController.getCategoryByID(category) == null) {
                    System.out.println("[!] There is no Category with ID: " + category);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID input!");
            }
        }
        //Qty
        while (true) {
            try {
                System.out.print("[~] Enter Product's Quantity: ");
                quantity = new Scanner(System.in).nextInt();
                if (quantity < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Quantity input! Make sure it contain only Number, No (.) and a Positive Number");
            }
        }
        //Unit Price
        while (true) {
            try {
                System.out.print("[~] Enter Unit Price: ");
                price = new Scanner(System.in).nextDouble();
                if (price < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Price input! Make sure it contain only Number, (.) and a Positive Number");
                ;
            }
        }

        if (desc.isEmpty()) {
            System.out.println("[!] All Fields Are Required!");
        } else {

            CreateProductReq createProductReq = new CreateProductReq(desc, category, quantity, price);

            boolean isAdd = productController.addProduct(createProductReq);
            if (isAdd) {
                System.out.println("[+] Product added successfully!");
            } else {
                System.out.println("[!] Error Adding Product!");
            }
        }
    }

    public static void updateProduct() {
        int id;
        String category = "";
        String quantity;
        String price;
        Product foundProduct;
        System.out.println("""
                *** Update Product ***
                """);
        //ID
        while (true) {
            try {
                System.out.print("[~] Enter Product's ID(-1: back): ");
                id = new Scanner(System.in).nextInt();
                if (id == -1) {
                    return;
                } else if (id < 0) {
                    System.out.println("[!] ID cannot be negative!");
                    continue;
                }
                foundProduct = productController.getProduct(id);
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID input!");
            }
        }

        if (foundProduct != null) {

            TableUIConfig.displayProduct(new ArrayList<>(List.of(productController.getProduct(id))));

            System.out.println("[!] Leave Blank if you don't want to Update the Field!!");
            System.out.print("[~] Enter New Product's description: ");
            String desc = new Scanner(System.in).nextLine();

            //Category
            int categoryID = -1;
            String inputCategory;

            while (true) {
                System.out.println("[~] Enter Product's CategoryID: ");
                for (Category c : categoryController.getCategories()) {
                    System.out.println("ID: " + c.getId() + " - " + c.getCategory_name());
                }
                System.out.print("[~] Option << ");
                inputCategory = new Scanner(System.in).nextLine();
                if (!inputCategory.isEmpty()) {
                    try {
                        categoryID = Integer.parseInt(inputCategory);
                        break;
                    } catch (Exception e) {
                        System.out.println("[!] Invalid ID input!");
                    }
                } else break;
            }

            //Qty
            int quantityValue = -1;
            while (true) {
                System.out.print("[~] Enter Product's Quantity: ");
                quantity = new Scanner(System.in).nextLine();
                if (quantity.isEmpty()) {
                    break;
                }
                try {
                    quantityValue = Integer.parseInt(quantity);
                    if (quantityValue < 0) {
                        System.out.println("[~] Invalid Quantity cannot be Negative!");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("[~] Invalid Quantity!");
                }
            }
            double priceValue = -1;
            //Unit Price
            while (true) {

                System.out.print("[~] Enter Unit Price: ");
                price = new Scanner(System.in).nextLine();
                if (price.isEmpty()) {
                    break;
                }
                try {
                    priceValue = Double.parseDouble(price);
                    if (priceValue < 0) {
                        System.out.println("[!] Invalid Price cannot be Negative!");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("[!] Invalid Price input! Make sure it contain only Number, (.) and a Positive Number");

                }
            }
            UpdateProductReq updateProductReq = new UpdateProductReq(desc, categoryID, quantityValue, priceValue);

            boolean isUpdate = productController.updateProduct(id, updateProductReq);
            if (isUpdate) {
                System.out.println("[+] Product updated successfully!");
                TableUIConfig.displayProduct(List.of(productController.getProduct(id)));
            } else {
                System.out.println("[!] Error Updating Product!");
            }
        } else {
            System.out.println("[!] Product ID does not exist!");
        }
    }

    public static void deleteProduct() {
        int id;
        System.out.println("""
                *** Delete Product ***
                """);

        while (true) {
            try {
                System.out.print("[~] Enter Product's ID (-1: back): ");
                id = new Scanner(System.in).nextInt();
                if (id == -1) {
                    return;
                } else if (id < 0) {
                    System.out.println("[!] ID cannot be negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid ID!");
            }
        }

        Product foundProduct = productController.getProduct(id);
        if (foundProduct != null) {
            String choice = "";
            while (true) {
                try {
                    System.out.println("Do you really want to delete this product? (Y/N)");
                    System.out.print("[~] Option << ");
                    choice = new Scanner(System.in).nextLine();
                } catch (Exception e) {
                    System.out.println("[!] Invalid Choice!");
                }
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    boolean isDelete = productController.deleteProduct(id);
                    if (isDelete) {
                        System.out.println("[+] Product deleted successfully!");
                        break;
                    } else {
                        System.out.println("[!]Error Deleting Product!");
                    }
                } else break;
            }
        } else {
            System.out.println("[!] Product with ID " + id + " does not exist!");
        }
    }

    public static void searchProduct() {
        int id;
        String desc;
        int op;
        System.out.println("""
                *** Search Product *** \n
                1. By ID
                2. By Name
                3. Exit
                """);


        while (true) {
            try {
                System.out.print("[~]Option << ");
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
                while (true) {
                    try {
                        System.out.print("[~] Enter Product's ID (-1: back): ");
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
                Product foundProduct = productController.getProducts()
                        .stream()
                        .filter(product -> product.getId() == finalId)
                        .findFirst()
                        .orElse(null);
                if (foundProduct != null) {
                    TableUIConfig.displayProduct(new ArrayList<>(List.of(foundProduct)));
                } else {
                    System.out.println("[!] Product's not found!");
                }
            }
            case 2 -> {
                System.out.print("[~] Enter Product's Name (-1: back): ");
                desc = new Scanner(System.in).nextLine();
                if (desc.equals("-1")) {
                    return;
                }
                Product foundProduct = productController.getProducts()
                        .stream()
                        .filter(product -> product.getProduct_name().equals(desc))
                        .findFirst()
                        .orElse(null);
                if (foundProduct != null) {
                    TableUIConfig.displayProduct(new ArrayList<>(List.of(foundProduct)));
                } else {
                    System.out.println("[!] Product's not found!");
                }
            }
        }
    }
}
