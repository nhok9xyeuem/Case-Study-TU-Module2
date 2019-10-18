package com.codegym.casestudymovieappdemo.repository;

import com.codegym.casestudymovieappdemo.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    Page<Movie> findAllByNameContaining(String name, Pageable pageable);
}
