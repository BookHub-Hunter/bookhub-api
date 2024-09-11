package com.ingsw.bookhubapi.repository;

import com.ingsw.bookhubapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
