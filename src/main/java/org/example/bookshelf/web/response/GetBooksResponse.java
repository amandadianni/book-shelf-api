package org.example.bookshelf.web.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Data
public class GetBooksResponse {

    private Page<GetBookResponse> books;

}
