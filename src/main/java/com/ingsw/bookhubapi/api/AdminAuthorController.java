package com.ingsw.bookhubapi.api;

import com.ingsw.bookhubapi.model.entity.Author;
import com.ingsw.bookhubapi.model.entity.Category;
import com.ingsw.bookhubapi.service.AdminAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/authors")
@RequiredArgsConstructor
public class AdminAuthorController {

    private final AdminAuthorService adminAuthorService;

    @GetMapping
    public ResponseEntity<List<Author>> listAll(){
        List<Author> authors = adminAuthorService.getAll();
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Author>> paginateAuthors(@PageableDefault(size=5, sort="firstName") Pageable pageable){
        Page<Author> authors = adminAuthorService.paginate(pageable);
        return new ResponseEntity<Page<Author>>(authors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createCategory(@RequestBody Author author){
        Author createdAuthor = adminAuthorService.create(author);
        return new ResponseEntity<Author>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Integer id){
        Author author = adminAuthorService.findById(id);
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author author){
        Author updatedAuthor = adminAuthorService.update(id, author);
        return new ResponseEntity<Author>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id){
        adminAuthorService.delete(id);
        return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }
}
