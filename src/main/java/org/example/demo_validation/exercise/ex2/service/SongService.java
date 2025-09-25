package org.example.demo_validation.exercise.ex2.service;

import org.example.demo_validation.exercise.ex2.model.Song;

import java.util.List;

public interface SongService {
    void save(Song song);
    List<Song> findAll();
    void update(Song song);
    void delete(Long id);
    Song findById(long id);
}
