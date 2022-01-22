package org.example.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
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

    private String imageUrl;

}
