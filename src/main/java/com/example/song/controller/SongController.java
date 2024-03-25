/*
 * 
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

// Write your code here

package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.song.service.SongH2Service;
import com.example.song.model.Song;

@RestController
public class SongController {
    @Autowired
    public SongH2Service service;

    @DeleteMapping("/songs/{songId}")
    public void delSong(@PathVariable("songId") int songId) {
        service.delSong(songId);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song so) {
        return service.updateSong(songId, so);
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return service.addSong(song);
    }

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return service.getSongs();
    }

    @GetMapping("/songs/{songId}")
    public Song getSongbyId(@PathVariable("songId") int songId) {
        return service.getSongbyId(songId);
    }

}
