package org.example.bookshelf.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.bookshelf.entity.Book;
import org.example.bookshelf.repository.BookRepository;
import org.example.bookshelf.web.request.PostBookRequest;
import org.example.bookshelf.web.response.PostBookResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository repository;

    @Test
    public void givenPostBook_withInvalidBody_willReturn400Status() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}")))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenPostBook_withValidBody_willCreateBookAndReturn200Status() throws Exception {
        String title = "How is it like to be Batman";
        String author = "Bruce Wayne";
        String publisher = "Wayne Industries";
        PostBookRequest request = PostBookRequest.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .build();

        Gson gson = new GsonBuilder().create();

        MvcResult result = this.mockMvc.perform((MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(request))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(author))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publisher").value(publisher))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        PostBookResponse response = gson.fromJson(json, PostBookResponse.class);
        Book persisted = repository.findById(response.getBookId()).orElse(null);
        Assert.assertNotNull(persisted);
        Assert.assertEquals(title, persisted.getTitle());
        Assert.assertEquals(author, persisted.getAuthor());
        Assert.assertEquals(publisher, persisted.getPublisher());

        // removing so it won't impact other test cases
        repository.deleteById(persisted.getId());
    }

    @Test
    public void getBooks_withNoPagination_willReturnDefaultPaginated() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.get("/books")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.content", Matchers.hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.totalElements").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.totalPages").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.offset").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.pageNumber").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.pageSize").value(10));
    }

    @Test
    public void getBooks_withPagination_willReturnPaginated() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.get("/books")
                        .param("page", "1")
                        .param("size", "4")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.content", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.size").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.totalElements").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.totalPages").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.offset").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.pageNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.pageSize").value(4));
    }

    @Test
    public void getBooks_withPaginationAndLastPage_willReturnSmallerPage() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.get("/books")
                        .param("page", "5")
                        .param("size", "3")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.content", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.size").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.totalElements").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.totalPages").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.offset").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.pageNumber").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books.pageable.pageSize").value(3));
    }

}