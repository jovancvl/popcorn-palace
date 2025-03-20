package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.entities.Movie;
import com.att.tdp.popcorn_palace.services.MovieService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.create(movie), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAll(){
        return new ResponseEntity<>(movieService.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> get(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.get(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.update(id, movie), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Movie> delete(@PathVariable Long id) {
        return movieService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostConstruct
    protected void initSomeMovies() {
        movieService.create(new Movie("Titanic", 1997, 8.9));
        movieService.create(new Movie("Ip Man", 1997, 8.9));
        movieService.create(new Movie("Ligma", 1997, 8.9));
        movieService.create(new Movie("Sugma", 1997, 8.9));
        movieService.create(new Movie("Socon", 1997, 8.9));
        movieService.create(new Movie("Chocon", 1997, 8.9));
    }
}
