package com.codegym.casestudymovieappdemo.repository;

import com.codegym.casestudymovieappdemo.model.Director;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DirectorRepository extends PagingAndSortingRepository<Director, Long> {
}
