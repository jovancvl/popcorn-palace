package com.att.tdp.popcorn_palace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // if this field appears in json when creating/updating a movie, Jackson won't set it
    private long id;
    @Column(unique = true)
    private String title = "";
    private int releaseYear = -1;
    private double rating = -1;

    public Movie(String title, int releaseYear, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    /**
     * Patches and checks if values are valid
     * @param m contains new values
     * @return true if new values are valid, false if not
     */
    public boolean patch(Movie m) {
        this.title       = m.title.isBlank() ? this.title       : m.title;
        this.releaseYear = m.releaseYear > 0 ? this.releaseYear : m.releaseYear;
        this.rating      =      m.rating > 0 ? this.rating      : m.rating;

        return this.isValid();
    }

    @JsonIgnore
    public boolean isValid() {
        if (title.isBlank()) return false;
        if (releaseYear < 0) return false;
        if (rating < 0) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                '}';
    }
}
