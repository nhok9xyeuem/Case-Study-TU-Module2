package com.codegym.casestudymovieappdemo.service;

import com.codegym.casestudymovieappdemo.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovieService {

    Page<Movie> findAllByNameContaining(String name, Pageable pageable);

    Page<Movie> findAll(Pageable pageable);

    Optional<Movie> findById(Long id);

    void save(Movie movie);

    void remove(Long id);
}
