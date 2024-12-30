package service;

import model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    boolean addCategory(String category_Name);

    boolean updateCategory(int id, String category_name);

    boolean deleteCategory(int categoryId);

    Category searchCategory(int categoryId) throws Exception;

    Category searchCategory(String category_name) throws Exception;
}
