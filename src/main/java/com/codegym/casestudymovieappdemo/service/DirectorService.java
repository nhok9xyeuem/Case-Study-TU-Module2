package com.codegym.casestudymovieappdemo.service;

import com.codegym.casestudymovieappdemo.model.Director;

import java.util.Optional;

public interface DirectorService {
    Iterable<Director> findAll();

    Optional<Director> findById(Long id);

    void save(Director director);

    void remove(Long id);
}
