package org.example.bookshelf.web.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class PostBookRequest {

    @NotNull
    private String title;
    @NotNull
    private String author;
    private String publisher;
    private String summary;

}
