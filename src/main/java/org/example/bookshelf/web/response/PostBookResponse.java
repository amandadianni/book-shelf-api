package org.example.bookshelf.web.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PostBookResponse {

    private UUID bookId;
    private String title;
    private String author;
    private String publisher;
    private String summary;

}
