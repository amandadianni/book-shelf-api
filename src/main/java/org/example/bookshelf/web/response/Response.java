package org.example.bookshelf.web.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<K> {
    K data;
}
