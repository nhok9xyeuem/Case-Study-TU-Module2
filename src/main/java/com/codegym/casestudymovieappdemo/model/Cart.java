package com.codegym.casestudymovieappdemo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Cart {
    private Map<Movie, Integer> movies = new HashMap<>();

    public Cart() {

    }

    public Cart(Map<Movie, Integer> movies) {
        this.movies = movies;
    }

    public Map<Movie, Integer> getMovies() {
        return movies;
    }

    private boolean checkItemInCart(Movie movie) {
        for (Map.Entry<Movie, Integer> entry : movies.entrySet()) {
            if (entry.getKey().getId().equals(movie.getId())) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Movie, Integer> selectItemInCart(Movie movie){
        for (Map.Entry<Movie, Integer> entry : movies.entrySet()) {
            if (entry.getKey().getId().equals(movie.getId())) {
                return entry;
            }
        }
        return null;
    }

    public void addMovie(Movie movie) {
        if(!checkItemInCart(movie)){
            movies.put(movie, 1);
        } else {
            Map.Entry<Movie, Integer> itemEntry = selectItemInCart(movie);
            Integer newQuanity = itemEntry.getValue() + 1;
            movies.replace(itemEntry.getKey(), newQuanity);
        }
    }

    public void removeMovie(Movie movie) {
        if (checkItemInCart(movie)){
            Map.Entry<Movie, Integer> itemEntry = selectItemInCart(movie);
            if(itemEntry.getValue() > 1){
                Integer newQuantity = itemEntry.getValue() - 1;
                movies.replace(itemEntry.getKey(), newQuantity);
            } else {
                movies.remove(itemEntry.getKey());
            }
        }
    }

    public Integer countMovieQuantity(){
        Integer movieQuantity = 0;
        for (Map.Entry<Movie, Integer> entry : movies.entrySet()) {
            movieQuantity += entry.getValue();
        }
        return movieQuantity;
    }

    public Integer countItemQuantity(){
        return movies.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Movie, Integer> entry : movies.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }

    @Override
    public String toString() {
        return "Cart{" +
                ", movies=" + movies +
                '}';
    }
}
