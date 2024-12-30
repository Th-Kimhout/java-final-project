package service;

import model.Category;
import repository.CategoryRepoImpl;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepoImpl categoryRepoImpl = new CategoryRepoImpl();

    @Override
    public List<Category> getCategories() {
        CategoryRepoImpl categoryRepoImpl = new CategoryRepoImpl();
        return categoryRepoImpl.getAllCategories();
    }

    @Override
    public boolean addCategory(String category_name) {
        return categoryRepoImpl.addCategory(category_name);
    }

    @Override
    public boolean updateCategory(int id, String category_name) {
        if (!category_name.isEmpty()) {
            if (categoryRepoImpl.getAllCategories().stream().anyMatch(category -> category.getId() == id)) {
                return categoryRepoImpl.updateCategory(id, category_name);
            }
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        if (categoryRepoImpl.getAllCategories().stream().anyMatch(category -> category.getId() == categoryId)) {
            return categoryRepoImpl.deleteCategoryByID(categoryId);
        }
        return false;
    }

    @Override
    public Category searchCategory(int categoryId) {
        return categoryRepoImpl.getAllCategories().stream().filter(c -> c.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category searchCategory(String category_name) {
        return categoryRepoImpl.getAllCategories().stream().filter(c -> c.getCategory_name().equalsIgnoreCase(category_name))
                .findFirst()
                .orElse(null);
    }

}
