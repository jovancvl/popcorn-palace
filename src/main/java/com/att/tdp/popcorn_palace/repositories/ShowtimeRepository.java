package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.entities.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
