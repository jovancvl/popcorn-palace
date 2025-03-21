package com.att.tdp.popcorn_palace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue
    private long id;
    private double price = -1;
    private String theater = "";
    private Instant startTime = Instant.EPOCH;
    private Instant endTime = Instant.EPOCH;
    @ManyToOne
    @JsonIgnore
    private Movie movie = null;

    public Showtime(double price, String theater, Instant startTime, Instant endTime, Movie movie) {
        this.price = price;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
    }

    public boolean patch(Showtime s) {
        this.price     =                        s.price > 0 ? s.price     : this.price;
        this.theater   =               !s.theater.isBlank() ? s.theater   : this.theater;
        this.startTime = s.startTime.isAfter(Instant.EPOCH) ? s.startTime : this.startTime;
        this.endTime   =   s.endTime.isAfter(Instant.EPOCH) ? s.endTime   : this.endTime;
        this.movie     =                    s.movie != null ? s.movie     : this.movie;

        return isValid();
    }

    @JsonIgnore
    public boolean isValid() {
        if (price < 0) return false;
        if (theater.isBlank()) return false;
        if (startTime.isAfter(endTime)) return false;
        if (movie == null) return false;
        return true;
    }

    public long getMovieId(){
        return movie.getId();
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "id=" + id +
                ", price=" + price +
                ", theater='" + theater + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", movie=" + movie +
                '}';
    }
}
