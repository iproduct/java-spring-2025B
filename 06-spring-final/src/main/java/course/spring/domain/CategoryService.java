package course.spring.domain;

import course.spring.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    List<Category> getCategoriesByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Category deleteCategoryById(Long id);
    long getCategoriesCount();
}
