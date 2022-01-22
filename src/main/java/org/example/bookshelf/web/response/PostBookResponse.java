package org.example.bookshelf.web.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostBookResponse {

    private UUID bookId;
    private String title;
    private String author;
    private String publisher;
    private String summary;
    private String imageUrl;

}
