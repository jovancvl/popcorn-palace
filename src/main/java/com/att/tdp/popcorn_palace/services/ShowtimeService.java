package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.dtos.ShowtimeDto;
import com.att.tdp.popcorn_palace.entities.Movie;
import com.att.tdp.popcorn_palace.entities.Showtime;
import com.att.tdp.popcorn_palace.exceptions.IncorrectFieldException;
import com.att.tdp.popcorn_palace.exceptions.ResourceAlreadyExistsException;
import com.att.tdp.popcorn_palace.exceptions.ResourceNotFoundException;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import com.att.tdp.popcorn_palace.repositories.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class ShowtimeService {
    
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    MovieRepository movieRepository;

    /**
     * Parses the map without doing any checks to see whether the fields are correct or not.
     * @param map to convert into a Showtime object
     * @return Showtime object
     */
    public Showtime parseMap(Map<String, Object> map) {
        double price;
        String theater;
        Instant startTime;
        Instant endTime;
        long movieId;
        try {
            price = (Double) map.get("price");
            theater = (String) map.get("theater");
            startTime = Instant.parse((String) map.get("startTime"));
            endTime = Instant.parse((String) map.get("endTime"));
            movieId = Long.valueOf((Integer) map.get("movieId"));
        } catch (Exception e) {
            throw new IncorrectFieldException("One or more fields are incorrect.");
        }

        Movie movie = new Movie();
        movie.setId(movieId);

        return new Showtime(price, theater, startTime, endTime, movie);
    }

    public Showtime create(Showtime showtime) {
        Movie movie = movieRepository.findById(showtime.getMovie().getId()).orElse(null);
        if (movie == null) {
            throw new IncorrectFieldException("One or more fields are incorrect.");
        }

        showtime.setMovie(movie);

        if (!showtime.isValid()) {
            throw new IncorrectFieldException("One or more fields are incorrect.");
        }

        return showtimeRepository.saveAndFlush(showtime);
    }

    public Showtime create(Map<String, Object> map){
        return this.create(this.parseMap(map));
    }

    public Showtime create(ShowtimeDto showtimeDto) {
        Movie movie = new Movie();
        movie.setId(showtimeDto.getMovieId());
        return this.create(new Showtime(showtimeDto.getPrice(), showtimeDto.getTheater(), showtimeDto.getStartTime(), showtimeDto.getEndTime(), movie));
    }

    public List<Showtime> get() {
        return showtimeRepository.findAll();
    }

    public Showtime get(Long id) {
        Showtime movie = showtimeRepository.findById(id).orElse(null);
        if (movie == null) {
            throw new ResourceNotFoundException("Resource with id " + id + " not found");
        }
        return movie;
    }

    public Showtime update(Long id, Showtime s) {
        Showtime oldShowtime = showtimeRepository.findById(id).orElse(null);
        if (oldShowtime == null) {
            throw new ResourceNotFoundException("Resource with id " + id + " not found");
        }

        if (!oldShowtime.patch(s)) {
            throw new IncorrectFieldException("One or more fields are incorrect.");
        }

        return showtimeRepository.saveAndFlush(oldShowtime);
    }

    public boolean delete(Long id) {
        if (!showtimeRepository.existsById(id)){
            throw new ResourceNotFoundException("Resource with id " + id + " not found");
        }
        showtimeRepository.deleteById(id);
        return !showtimeRepository.existsById(id);
    }
}
