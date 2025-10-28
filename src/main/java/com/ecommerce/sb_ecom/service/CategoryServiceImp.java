package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private long nextId = 1l;

    @Override
    public List<Category> getAllCategories() {

        return categories;
    }

    @Override
    public void createCategory(Category category) {
            category.setCategoryId(nextId++);
            categories.add(category);
    }
    @Override
    public String deleteCategory(Long categoryId){
        Category category = categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        categories.remove(category);
        return "categories with categoryId: "+categoryId +" deleted successfully.";
    }
}
