package com.ingsw.bookhubapi.service.impl;

import com.ingsw.bookhubapi.model.entity.Category;
import com.ingsw.bookhubapi.repository.CategoryRepository;
import com.ingsw.bookhubapi.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> paginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Category create(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category update(Integer id, Category updatedCategory) {
        Category categoryFromDb = findById(id);
        categoryFromDb.setName(updatedCategory.getName());
        categoryFromDb.setDescription(updatedCategory.getDescription());
        categoryFromDb.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(categoryFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Category categoryFromDb = findById(id);
        categoryRepository.delete(categoryFromDb);
    }
}
