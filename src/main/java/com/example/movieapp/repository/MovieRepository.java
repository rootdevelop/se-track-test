package com.example.movieapp.repository;

import com.example.movieapp.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>
{
    long countByWatchedEquals(boolean watched);
}
