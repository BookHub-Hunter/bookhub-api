package com.ingsw.bookhubapi.api;

import com.ingsw.bookhubapi.model.entity.Author;
import com.ingsw.bookhubapi.model.entity.Book;
import com.ingsw.bookhubapi.service.AdminAuthorService;
import com.ingsw.bookhubapi.service.AdminBookService;
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
@RequestMapping("/admin/books")
public class AdminBookController {
    private final AdminBookService adminBookService;

    @GetMapping
    public ResponseEntity<List<Book>> listAll(){
        List<Book> books = adminBookService.getAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Book>> paginateBooks(@PageableDefault(size=5, sort="title") Pageable pageable){
        Page<Book> books = adminBookService.paginate(pageable);
        return new ResponseEntity<Page<Book>>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book createdBook = adminBookService.create(book);
        return new ResponseEntity<Book>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id){
        Book book = adminBookService.findById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book book){
        Book updatedBook = adminBookService.update(id, book);
        return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id){
        adminBookService.delete(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
}
