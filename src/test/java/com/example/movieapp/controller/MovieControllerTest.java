package com.example.movieapp.controller;

import com.example.movieapp.manager.MovieManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieManager movieManager;

    @Test
    public void showMovies() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

    }

    @Test
    public void addMovie() throws Exception {

        mockMvc.perform(post("/addMovie"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void watched() throws Exception {

        mockMvc.perform(get("/switch/10"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(get("/delete/10"))
                .andExpect(status().is3xxRedirection());
    }

}