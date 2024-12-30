package view;

import controller.CategoryController;
import model.Category;
import model.Product;
import utilies.TableUIConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryMenu {
    private static final CategoryController categoryController = new CategoryController();

    public static void listCategories() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Display Categories ***
                """);
        List<Category> categories = categoryController.getCategories();
        if (!categories.isEmpty()) {
            TableUIConfig.DisplayCategory(categories);
        } else {
            System.out.println("[!] No categories found");
        }
    }

    public static void addCategory() {
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Add Category ***
                """);
        while (true) {
            System.out.print("[~] Input Category Description (-1: back): ");
            String categoryDes = new Scanner(System.in).nextLine();
            if (categoryDes.equals("-1")) {
                return;
            } else if (categoryDes.isEmpty()) {
                System.out.println("[!] All Fields Required!");

            } else {
                boolean isAdded = categoryController.addCategory(categoryDes);

                if (isAdded) {
                    System.out.println("[+] Category Added!");
                    TableUIConfig.DisplayCategory(List.of(categoryController.getCategoryByName(categoryDes)));
                } else System.out.println("[!] Error Adding Category!");
            }
        }
    }

    public static void updateCategory() {
        int categoryID;
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Update Category ***
                """);
        while (true) {
            try {
                System.out.print("[~] Input Category ID(-1: back): ");
                categoryID = new Scanner(System.in).nextInt();
                if (categoryID == -1) {
                    return;
                }
                if (categoryID < 0) {
                    System.out.println("[!] Category ID cannot be negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Category ID!");
            }
        }
        Category foundCategory = categoryController.getCategoryByID(categoryID);
        if (foundCategory == null) {
            System.out.println("[!] Category's not found!");
        } else {
            TableUIConfig.DisplayCategory(List.of(categoryController.getCategoryByID(categoryID)));
            System.out.print("[~] Input New Category Description: ");
            String Desc = new Scanner(System.in).nextLine();
            boolean isUpdated = categoryController.updateCategory(categoryID, Desc);
            if (isUpdated) {
                System.out.println("[+] Category Updated!");
                TableUIConfig.DisplayCategory(List.of(categoryController.getCategoryByID(categoryID)));
            } else System.out.println("[!] Error Updating Category!");
        }
    }

    public static void deleteCategory() {
        int categoryID;
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Delete Category ***
                """);
        System.out.print("[~] Input Category ID: ");
        while (true) {
            try {
                categoryID = new Scanner(System.in).nextInt();
                if (categoryID < 0) {
                    System.out.println("[!] Category ID cannot be negative!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("[!] Invalid Category ID!");
            }
        }
        Category foundCategory = categoryController.getCategoryByID(categoryID);
        if (foundCategory != null) {
            String choice = "";
            while (true) {
                try {
                    System.out.println("Do you really want to delete this category? (Y/N)");
                    System.out.print("[~] Option << ");
                    choice = new Scanner(System.in).nextLine();
                } catch (Exception e) {
                    System.out.println("[!] Invalid Choice!");
                }
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    boolean isDeleted = categoryController.deleteCategory(categoryID);
                    if (isDeleted) {
                        System.out.println("[+] Category Deleted!");
                        break;
                    } else System.out.println("[!] Error Deleting Category!");
                } else break;
            }
        } else {
            System.out.println("[!] Category not found");
        }
    }

    public static void searchCategory() {
        int id;
        String desc;
        int op;
        System.out.println("=".repeat(25));
        System.out.println("""
                *** Search Category *** \n
                1. By ID
                2. By Description
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
                System.out.println("[!] Invalid Option!");
            }
        }

        switch (op) {
            case 1 -> {

                while (true) {
                    try {
                        System.out.print("[~] Enter Category's ID (-1: back): ");
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
                Category foundCategory = categoryController.getCategories()
                        .stream()
                        .filter(product -> product.getId() == finalId)
                        .findFirst()
                        .orElse(null);
                if (foundCategory != null) {
                    TableUIConfig.DisplayCategory(List.of(foundCategory));
                } else {
                    System.out.println("[!] Category's not found!");
                }
            }
            case 2 -> {
                System.out.print("[~] Enter Category's Description (-1: back): ");
                desc = new Scanner(System.in).nextLine();
if (desc.equals("-1")) {
    return;
}
                Category foundCategory = categoryController.getCategories()
                        .stream()
                        .filter(category -> category.getCategory_name().equalsIgnoreCase(desc))
                        .findFirst()
                        .orElse(null);
                if (foundCategory != null) {
                    TableUIConfig.DisplayCategory(List.of(foundCategory));
                } else {
                    System.out.println("[!] Category's not found!");
                }
            }
        }
    }

}
