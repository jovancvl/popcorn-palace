package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.entities.Movie;
import com.att.tdp.popcorn_palace.exceptions.IncorrectFieldException;
import com.att.tdp.popcorn_palace.exceptions.ResourceAlreadyExistsException;
import com.att.tdp.popcorn_palace.exceptions.ResourceNotFoundException;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie create(Movie movie) {
        if (!movie.isValid()) {
            throw new IncorrectFieldException("One or more fields are incorrect.");
        }

        if (movieRepository.existsByTitle(movie.getTitle())){
            throw new ResourceAlreadyExistsException("Movie with this title already exists");
        }

        return movieRepository.saveAndFlush(movie);
    }

    public List<Movie> get() {
        return movieRepository.findAll();
    }

    public Movie get(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            throw new ResourceNotFoundException("Resource with id " + id + " not found");
        }
        return movie;
    }

    public Movie update(Long id, Movie m) {
        Movie oldMovie = movieRepository.findById(id).orElse(null);
        if (oldMovie == null) {
            throw new ResourceNotFoundException("Resource with id " + id + " not found");
        }

        if (!oldMovie.patch(m)) {
            throw new IncorrectFieldException("One or more fields are incorrect.");
        }

        return movieRepository.saveAndFlush(oldMovie);
    }

    public boolean delete(Long id) {
        if (!movieRepository.existsById(id)){
            throw new ResourceNotFoundException("Resource with id " + id + " not found");
        }
        movieRepository.deleteById(id);
        return !movieRepository.existsById(id);
    }
}
