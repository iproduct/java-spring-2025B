package course.spring.domain.impl;

import course.spring.dao.CategoryRepository;
import course.spring.domain.CategoryService;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NonexistingEntityException(
                    String.format("Category with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByNameContaining(name);
    }

    @Override
    public Category addCategory(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        getCategoryById(category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategoryById(Long id) {
        var old = getCategoryById(id);
        categoryRepository.deleteById(id);
        return old;
    }

    @Override
    public long getCategoriesCount() {
        return categoryRepository.count();
    }
}
