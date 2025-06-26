package course.spring.domain.impl;

import course.spring.dao.CategoryRepository;
import course.spring.domain.CategoryService;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NonexistingEntityException(
                    String.format("Category with ID='%s' does not exist.", id)
        ));
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public long getCategoriesCount() {
        return categoryRepository.count();
    }
}
