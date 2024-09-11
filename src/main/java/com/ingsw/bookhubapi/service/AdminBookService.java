package com.ingsw.bookhubapi.service;

import com.ingsw.bookhubapi.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminBookService {
    List<Book> getAll();
    Page<Book> paginate(Pageable pageable);
    Book findById(Integer id);
    Book create(Book book);
    Book update(Integer id, Book updatedBook);
    void delete(Integer id);
}
