package com.att.tdp.popcorn_palace.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeDto {
    private long id = -1;
    private double price = -1;
    private String theater = "";
    private Instant startTime = Instant.EPOCH;
    private Instant endTime = Instant.EPOCH;
    private long movieId = -1;
}
