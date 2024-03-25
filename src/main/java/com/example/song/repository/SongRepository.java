// Write your code here
package com.example.song.repository;

import java.util.*;

import com.example.song.model.Song;

public interface SongRepository {
    ArrayList<Song> getSongs();

    Song getSongbyId(int songId);

    Song addSong(Song song);

    Song updateSong(int songId, Song so);

    void delSong(int songId);
}