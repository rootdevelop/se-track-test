package com.example.movieapp.manager;

import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieManager {

    @Autowired
    private final MovieRepository movieRepository;

    public MovieManager(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public long getMoviesCount() {
        return movieRepository.count();
    }

    public long getMoviesCountWatched() {
        return movieRepository.countByWatchedEquals(true);
    }

    public void addMovie(String name) {

        Movie m = new Movie(name);
        m.setName(name);
        m.setWatched(false);

        movieRepository.save(m);
    }

    public void setMovieWatched(int id) {
        Movie m = movieRepository.findOne(id);
        m.setWatched(!m.isWatched());
        movieRepository.save(m);

    }

    public void deleteMovie(int id) {
        movieRepository.delete(id);
    }
}
