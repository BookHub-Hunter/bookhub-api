package com.ingsw.bookhubapi.repository;

import com.ingsw.bookhubapi.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
