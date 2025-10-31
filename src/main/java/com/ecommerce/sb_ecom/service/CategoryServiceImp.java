package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.repo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
//    private List<Category> categories = new ArrayList<>();
//    private long nextId = 1l;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
//            category.setCategoryId(nextId++);
            categoryRepository.save(category);
    }
    @Override
    public String deleteCategory(Long categoryId){
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        categoryRepository.delete(category);
        return "categories with categoryId: "+categoryId +" deleted successfully.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId ){
        List<Category> categories = categoryRepository.findAll();
    Optional<Category> optionalCategory = categories.stream()
            .filter(c->c.getCategoryId().equals(categoryId))
            .findFirst();
    if(optionalCategory.isPresent()){
        Category existingCategory = optionalCategory.get();
        existingCategory.setCategoryName(category.getCategoryName());
        Category savedCategory = categoryRepository.save(existingCategory);
        return savedCategory;
    }
    else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
    }

    }
}
