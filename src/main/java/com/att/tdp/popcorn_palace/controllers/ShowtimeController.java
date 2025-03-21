package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.dtos.ShowtimeDto;
import com.att.tdp.popcorn_palace.entities.Showtime;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    
    @Autowired
    ShowtimeService showtimeService;

    @PostMapping("/createFromMap")
    public ResponseEntity<Showtime> createFromMap(@RequestBody Map<String, Object> showtimeMap) {
        return new ResponseEntity<>(showtimeService.create(showtimeMap), HttpStatus.CREATED);
    }

    @PostMapping("/createFromDto")
    public ResponseEntity<Showtime> createFromDto(@RequestBody ShowtimeDto showtimeDto) {
        return new ResponseEntity<>(showtimeService.create(showtimeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Showtime>> getAll(){
        return new ResponseEntity<>(showtimeService.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> get(@PathVariable Long id) {
        return new ResponseEntity<>(showtimeService.get(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Showtime> update(@PathVariable Long id, @RequestBody Showtime showtime) {
        return new ResponseEntity<>(showtimeService.update(id, showtime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Showtime> delete(@PathVariable Long id) {
        return showtimeService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody Map<String, Instant> map) {
        Instant time = map.get("time");
        System.out.println(time.getClass().getName());
        System.out.println(Timestamp.from(time));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
