/*
 * 
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here

package com.example.song.service;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.song.model.Song;
import com.example.song.repository.SongRepository;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.model.SongRowMapper;

@Service
public class SongH2Service implements SongRepository {
  @Autowired
  private JdbcTemplate db;

  @Override
  public void delSong(int songId) {
    db.update("delete from playlist where songId = ?", songId);
  }

  @Override
  public ArrayList<Song> getSongs() {
    List<Song> SongCollections = db.query("SELECT * from playlist", new SongRowMapper());
    ArrayList<Song> arr = new ArrayList<>(SongCollections);
    return arr;
  }

  @Override
  public Song addSong(Song song) {
    db.update("insert into playlist(songName, lyricist, singer, musicDirector) values (?,?,?,?)", song.getSongName(),
        song.getLyricist(), song.getSinger(), song.getMusicDirector());

    Song savedSong = db.queryForObject(
        "select * from playlist where songName = ? and lyricist = ? and singer = ? and musicDirector = ?",
        new SongRowMapper(), song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());

    return savedSong;

  }

  @Override
  public Song getSongbyId(int songId) {
    try {
      Song getId = db.queryForObject("SELECT * from playlist WHERE songId = ?", new SongRowMapper(), songId);
      return getId;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override

  public Song updateSong(int songId, Song so) {

    if (so.getSongName() != null) {
      db.update("UPDATE playlist SET songName = ? WHERE songId = ?", so.getSongName(), songId);
    }
    if (so.getLyricist() != null) {
      db.update("UPDATE playlist SET lyricist = ? WHERE songId = ?", so.getLyricist(), songId);
    }
    if (so.getSinger() != null) {
      db.update("UPDATE playlist SET singer = ? WHERE songId = ?", so.getSinger(), songId);
    }

    if (so.getMusicDirector() != null) {
      db.update("UPDATE playlist SET musicDirector = ? WHERE songId = ?", so.getMusicDirector(), songId);
    }

    return getSongbyId(songId);

  }
}
