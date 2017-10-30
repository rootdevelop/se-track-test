package com.example.movieapp.controller;

import com.example.movieapp.manager.MovieManager;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * Movie controller
 */
@Controller
public class MovieController {

	/**
	 * Movie Manager responsible for managing all movie logic
	 */
	final MovieManager movieManager;

	@Autowired
	public MovieController(MovieManager movieManager) {
		this.movieManager = movieManager;
	}

	/**
	 * Show all movies
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String showMovies(Model model) {

		model.addAttribute("movies", movieManager.getAllMovies());
		model.addAttribute("movies_count", movieManager.getMoviesCount());
		model.addAttribute("movies_watched", movieManager.getMoviesCountWatched());
		return "all-movies";
	}

	/**
	 * Add a new movie
	 *
	 * @param model
	 * @param map
	 * @return
	 */
	@PostMapping("/addMovie")
	public RedirectView addMovie(Model model, @RequestParam Map<String, String> map) {

		movieManager.addMovie(map.get("name"));

		return new RedirectView("/");
	}

	/**
	 * Switches movie to watched or unwatched
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/switch/{id}")
	public RedirectView watched(Model model, @PathVariable int id) {

		movieManager.setMovieWatched(id);

		return new RedirectView("/");
	}

	/**
	 * Delete movie
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public RedirectView delete(Model model, @PathVariable int id) {

		movieManager.deleteMovie(id);

		return new RedirectView("/");
	}

}
