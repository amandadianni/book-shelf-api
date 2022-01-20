package org.example.bookshelf.service;

import lombok.RequiredArgsConstructor;
import org.example.bookshelf.entity.Book;
import org.example.bookshelf.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Page<Book> getBooks(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }


}
