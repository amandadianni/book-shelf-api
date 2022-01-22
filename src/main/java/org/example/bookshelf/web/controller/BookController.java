package org.example.bookshelf.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshelf.entity.Book;
import org.example.bookshelf.entity.PitchDeck;
import org.example.bookshelf.service.BookService;
import org.example.bookshelf.web.request.PostBookRequest;
import org.example.bookshelf.web.response.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/books", produces="application/json")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public PostBookResponse postBook(@RequestBody @Valid PostBookRequest request) {
        Book book = service.createBook(
                Book.builder()
                        .id(UUID.randomUUID())
                        .title(request.getTitle())
                        .author(request.getAuthor())
                        .publisher(request.getPublisher())
                        .summary(request.getSummary())
                        .imageUrl(request.getImageUrl())
                        .build());
        return PostBookResponse.builder()
                .bookId(book.getId())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .summary(book.getSummary())
                .imageUrl(book.getImageUrl())
                .build();
    }

    @GetMapping
    public GetBooksResponse getBooks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<Book> bookPage = service.getBooks(page, size);
        return GetBooksResponse.builder().books(bookPage.map(book ->
                        GetBookResponse.builder()
                                .bookId(book.getId())
                                .title(book.getTitle())
                                .author(book.getAuthor())
                                .publisher(book.getPublisher())
                                .summary(book.getSummary())
                                .imageUrl(book.getImageUrl())
                                .build()))
                        .build();
    }

}
