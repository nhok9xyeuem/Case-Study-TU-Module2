package com.codegym.casestudymovieappdemo.service;

import com.codegym.casestudymovieappdemo.model.Movie;
import com.codegym.casestudymovieappdemo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Page<Movie> findAllByNameContaining(String name, Pageable pageable){
        return movieRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<Movie> findAll(Pageable pageable){
        return movieRepository.findAll(pageable);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void remove(Long id) {
        movieRepository.deleteById(id);
    }
}

