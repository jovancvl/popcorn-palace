package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.entities.Movie;
import com.att.tdp.popcorn_palace.entities.Showtime;
import com.att.tdp.popcorn_palace.services.MovieService;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class Init {

    @Autowired
    MovieService movieService;
    @Autowired
    ShowtimeService showtimeService;

    @PostConstruct
    protected void initSomeMovies() {
        Movie m1 = movieService.create(new Movie("Titanic", 1997, 8.9));
        Movie m2 = movieService.create(new Movie("Ip Man", 1997, 8.9));
        Movie m3 = movieService.create(new Movie("Ligma", 1997, 8.9));
        Movie m4 = movieService.create(new Movie("Sugma", 1997, 8.9));
        Movie m5 = movieService.create(new Movie("Socon", 1997, 8.9));
        Movie m6 = movieService.create(new Movie("Chocon", 1997, 8.9));

        Showtime s1 = showtimeService.create(new Showtime(5.99, "Roda", Instant.now(), Instant.now().plus(Duration.ofHours(1)), m1));
        Showtime s2 = showtimeService.create(new Showtime(5.99, "Delta", Instant.now(), Instant.now().plus(Duration.ofHours(1)), m1));
        Showtime s3 = showtimeService.create(new Showtime(5.99, "Usce", Instant.now(), Instant.now().plus(Duration.ofHours(1)), m2));
        Showtime s4 = showtimeService.create(new Showtime(5.99, "Tako", Instant.now(), Instant.now().plus(Duration.ofHours(1)), m3));
    }
}
