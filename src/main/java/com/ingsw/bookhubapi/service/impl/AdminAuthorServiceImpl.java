package com.ingsw.bookhubapi.service.impl;


import com.ingsw.bookhubapi.model.entity.Author;
import com.ingsw.bookhubapi.repository.AuthorRepository;
import com.ingsw.bookhubapi.service.AdminAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminAuthorServiceImpl implements AdminAuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Author> paginate(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Transactional
    @Override
    public Author create(Author author) {
        author.setCreatedAt(LocalDateTime.now());
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author update(Integer id, Author updatedAuthor) {
        Author authorFromDb = findById(id);
        authorFromDb.setFirstName(updatedAuthor.getFirstName());
        authorFromDb.setLastName(updatedAuthor.getLastName());
        authorFromDb.setBio(updatedAuthor.getBio());
        authorFromDb.setUpdatedAt(LocalDateTime.now());
        return authorRepository.save(authorFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Author authorFromDb = findById(id);
        authorRepository.delete(authorFromDb);
    }
}
