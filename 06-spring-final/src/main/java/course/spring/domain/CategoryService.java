package course.spring.domain;

import course.spring.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategorys();
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Category deleteCategoryById(Long id);
    long getCategorysCount();
}
