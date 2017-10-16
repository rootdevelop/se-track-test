package com.example.movieapp.controller;

import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * Movie controller
 */
@Controller
public class MovieController {

    /**
     * Movie Repository containing all movies
     */
    @Autowired
    MovieRepository movieRepository;

    /**
     * Show all movies
     * @param model
     * @return
     */
    @GetMapping("/")
    public String showMovies(Model model) {

        model.addAttribute("movies",movieRepository.findAll());
        model.addAttribute("movies_count",movieRepository.count());
        model.addAttribute("movies_watched",movieRepository.countByWatchedEquals(true));
        return "all-movies";
    }

    /**
     * Add a new movie
     * @param model
     * @param map
     * @return
     */
    @PostMapping("/addMovie")
    public RedirectView addMovie(Model model, @RequestParam Map<String, String> map) {

        Movie m = new Movie();
        m.setName(map.get("name"));
        m.setWatched(false);

        movieRepository.save(m);

        return new RedirectView("/");
    }

    /**
     * Switches movie to watched or unwatched
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/switch/{id}")
    public RedirectView watched(Model model, @PathVariable int id) {

        Movie m = movieRepository.findOne(id);
        m.setWatched(!m.isWatched());
        movieRepository.save(m);

        return new RedirectView("/");
    }

    /**
     * Delete movie
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public RedirectView delete(Model model, @PathVariable int id) {

        movieRepository.delete(id);

        return new RedirectView("/");
    }

}
