package repository;

import model.Category;

import java.util.List;

public interface CategoryRepo {
    List<Category> getAllCategories();
    boolean addCategory(String category_name);
    boolean updateCategory(int id, String category_name);
    boolean deleteCategoryByID(int categoryID);

}
