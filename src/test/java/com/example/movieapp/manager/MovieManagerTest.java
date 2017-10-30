package com.example.movieapp.manager;

import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MovieManagerTest {

    private MovieRepository movieRepository;
    private MovieManager movieManager;

    private List<Movie> movies = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        movieRepository = mock(MovieRepository.class);
        movieManager = new MovieManager(movieRepository);

        movies.add(new Movie("Movie 1"));
        movies.add(new Movie("Movie 2"));

    }

    @Test
    public void getAllMovies() throws Exception {

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> actualMovies = new ArrayList<>();

        Iterable<Movie> list = movieManager.getAllMovies();

        for (Movie movie : list) {
            actualMovies.add(movie);
        }

        assertArrayEquals(movies.toArray(), actualMovies.toArray());

    }

    @Test
    public void getMoviesCount() throws Exception {

        long count = 42;

        when(movieRepository.count()).thenReturn(count);

        assertEquals(count, movieManager.getMoviesCount());

    }

    @Test
    public void getMoviesCountWatched() throws Exception {

        long count = 1337;

        when(movieRepository.countByWatchedEquals(true)).thenReturn(count);

        assertEquals(count, movieManager.getMoviesCountWatched());

    }

    @Test
    public void addMovie() throws Exception {
        Movie movie = new Movie("TestMovie");

        when(movieRepository.save((Movie) anyObject())).then(invocationOnMock -> movies.add(movie));

        movieManager.addMovie("TestMovie");

        assertEquals(3, movies.size());
    }

    @Test
    public void setMovieWatched() throws Exception {


        when(movieRepository.findOne(42)).thenReturn(movies.get(0));
        when(movieRepository.save((Movie) anyObject())).then(invocationOnMock -> {
            movies.get(0).setWatched(true);
            return null;
        });

        movieManager.setMovieWatched(42);

        assertTrue(movies.get(0).isWatched());
    }

    @Test
    public void deleteMovie() throws Exception {

        movieManager.deleteMovie(1337);
    }

}