package controller;

import model.Category;
import service.CategoryServiceImpl;

import java.util.List;
import java.util.TreeMap;

public class CategoryController {
    CategoryServiceImpl service = new CategoryServiceImpl();

    public List<Category> getCategories() {
        return service.getCategories();
    }

    public boolean addCategory(String categoryName) {
        return service.addCategory(categoryName);
    }

    public boolean updateCategory(int id, String categoryName) {
        return service.updateCategory(id, categoryName);
    }

    public boolean deleteCategory(int id) {
        return service.deleteCategory(id);
    }

    public Category getCategoryByID(int id)  {
        return service.searchCategory(id);
    }

    public Category getCategoryByName(String name)  {
        return service.searchCategory(name);
    }
}
