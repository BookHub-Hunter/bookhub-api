package com.ingsw.bookhubapi.service;

import com.ingsw.bookhubapi.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCategoryService {
    List<Category> getAll();
    Page<Category> paginate(Pageable pageable);
    Category findById(Integer id);
    Category create(Category category);
    Category update(Integer id, Category updatedCategory);
    void delete(Integer id);
}
