drop table book;

CREATE TABLE book
(
    id uuid primary key not null,
    title varchar not null,
    author varchar,
    publisher varchar,
    summary varchar,
    created_at TIMESTAMP WITH TIME ZONE,
    image_url clob

);

insert into book (id, title, author, publisher, summary, created_at, image_url) values ('e778f586-d07c-402a-8ea6-f743f1781859', 'Harry Potter and the Philosopher''s Stone', 'J. K. Rowling', 'Bloomsbury', 'Harry Potter and the Philosopher''s Stone is an enthralling start to Harry''s journey toward coming to terms with his past and facing his future.', '2022-01-05 19:20:21-02:00', '');
insert into book (id, title, author, publisher, summary, created_at, image_url) values ('8153980c-1eb5-4c99-96d3-72eea807732e', 'The Hobbit', 'J. R. R. Tolkien', 'George Allen & Unwin', 'The Hobbit is a book full of adventure, but it also has an underlying theme of perseverance and how former foes can work together to defeat a common enemy.', '2022-01-05 19:20:21-02:00', '');
