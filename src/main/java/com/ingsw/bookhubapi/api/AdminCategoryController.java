package com.ingsw.bookhubapi.api;

import com.ingsw.bookhubapi.model.entity.Category;
import com.ingsw.bookhubapi.service.AdminCategoryService;
import com.ingsw.bookhubapi.service.impl.AdminCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;    //Interface

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = adminCategoryService.getAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Category>> paginateCategories(@PageableDefault(size=5, sort="name") Pageable pageable){
        Page<Category> categories = adminCategoryService.paginate(pageable);
        return new ResponseEntity<Page<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createdCategory = adminCategoryService.create(category);
        return new ResponseEntity<Category>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id){
        Category category = adminCategoryService.findById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category){
        Category updatedCategory = adminCategoryService.update(id, category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id){
        adminCategoryService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
