package com.ingsw.bookhubapi.repository;

import com.ingsw.bookhubapi.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
