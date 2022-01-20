package org.example.bookshelf.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {

    @Id
    private UUID id;

    private String title;

    private String author;

    private String publisher;

    private String summary;

    @CreatedDate
    private Instant createdAt;

//    @Lob
//    private byte[] cover;

}
