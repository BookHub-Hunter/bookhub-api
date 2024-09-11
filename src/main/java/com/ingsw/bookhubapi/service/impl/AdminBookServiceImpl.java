package com.ingsw.bookhubapi.service.impl;

import com.ingsw.bookhubapi.model.entity.Author;
import com.ingsw.bookhubapi.model.entity.Book;
import com.ingsw.bookhubapi.model.entity.Category;
import com.ingsw.bookhubapi.repository.AuthorRepository;
import com.ingsw.bookhubapi.repository.BookRepository;
import com.ingsw.bookhubapi.repository.CategoryRepository;
import com.ingsw.bookhubapi.service.AdminBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminBookServiceImpl implements AdminBookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> paginate(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Transactional
    @Override
    public Book create(Book book) {
        Category category = categoryRepository.findById(book.getCategory().getId()).orElseThrow(() -> new RuntimeException("Category not found with id: " + book.getCategory().getId()));
        Author author = authorRepository.findById(book.getAuthor().getId()).orElseThrow(() -> new RuntimeException("Author not found with id: " + book.getAuthor().getId()));
        book.setCategory(category);
        book.setAuthor(author);
        book.setCreatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Integer id, Book updatedBook) {
        Book bookFromDb = findById(id);

        Category category = categoryRepository.findById(updatedBook.getCategory().getId()).orElseThrow(() -> new RuntimeException("Category not found with id: " + updatedBook.getCategory().getId()));
        Author author = authorRepository.findById(updatedBook.getAuthor().getId()).orElseThrow(() -> new RuntimeException("Author not found with id: " + updatedBook.getAuthor().getId()));

        bookFromDb.setTitle(updatedBook.getTitle());
        bookFromDb.setDescription(updatedBook.getDescription());
        bookFromDb.setPrice(updatedBook.getPrice());
        bookFromDb.setSlug(updatedBook.getSlug());
        bookFromDb.setCoverPath(updatedBook.getCoverPath());
        bookFromDb.setFilePath(updatedBook.getFilePath());
        bookFromDb.setCategory(category);
        bookFromDb.setAuthor(author);
        bookFromDb.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(bookFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Book bookFromDb = findById(id);
        bookRepository.delete(bookFromDb);
    }
}
