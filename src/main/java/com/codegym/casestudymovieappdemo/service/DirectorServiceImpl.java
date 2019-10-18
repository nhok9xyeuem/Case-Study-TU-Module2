package com.codegym.casestudymovieappdemo.service;

import com.codegym.casestudymovieappdemo.model.Director;
import com.codegym.casestudymovieappdemo.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DirectorServiceImpl implements DirectorService{

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Iterable<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Optional<Director> findById(Long id){
        return directorRepository.findById(id);
    }

    @Override
    public void save(Director director){
        directorRepository.save(director);
    }

    @Override
    public void remove(Long id){
        directorRepository.deleteById(id);
    }
}
