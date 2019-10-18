package com.codegym.casestudymovieappdemo.controller;

import com.codegym.casestudymovieappdemo.model.Cart;
import com.codegym.casestudymovieappdemo.model.Director;
import com.codegym.casestudymovieappdemo.model.Movie;
import com.codegym.casestudymovieappdemo.service.DirectorService;
import com.codegym.casestudymovieappdemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;

    @ModelAttribute("directors")
    public Iterable<Director> directors(){
        return directorService.findAll();
    }

    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("movie/create");
        modelAndView.addObject("movie", new Movie());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveMovie(@Validated @ModelAttribute Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("movie/create");
        }
        movieService.save(movie);
        ModelAndView modelAndView = new ModelAndView("movie/create");
        modelAndView.addObject("movie", new Movie());
        modelAndView.addObject("message", "New movie created sucessfully");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listMovies(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Movie> movies;
        if(s.isPresent()){
            movies = movieService.findAllByNameContaining(s.get(), pageable);
        } else {
            movies = movieService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("movie/list");
        modelAndView.addObject("movies", movies);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        if(movie != null){
            ModelAndView modelAndView = new ModelAndView("movie/edit");
            modelAndView.addObject("movie", movie);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@Validated @ModelAttribute Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("movie/edit");
        }
        movieService.save(movie);
        ModelAndView modelAndView = new ModelAndView("movie/edit");
        modelAndView.addObject("movie", movie);
        modelAndView.addObject("message", "Movie updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        if(movie != null){
            ModelAndView modelAndView = new ModelAndView("movie/delete");
            modelAndView.addObject("movie", movie.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteMovie(@ModelAttribute("movie") Movie movie){
        movieService.remove(movie.getId());
        return "redirect:list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewMovie(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        ModelAndView modelAndView = new ModelAndView("movie/view");
        modelAndView.addObject("movie", movie.get());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart, @RequestParam("action") String action, Pageable pageable){
        ModelAndView modelAndView;
        Optional<Movie> movie = movieService.findById(id);
        if (action.equals("show")){
            modelAndView = new ModelAndView("cart/show");
        } else {
            Page<Movie> movies = movieService.findAll(pageable);
            modelAndView = new ModelAndView("movie/list","movies",movies);
        }
        cart.addMovie(movie.get());
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeFromCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart, @RequestParam("action") String action, Pageable pageable){
        ModelAndView modelAndView;
        Optional<Movie> movie = movieService.findById(id);
        if (action.equals("show")){
            modelAndView = new ModelAndView("cart/show");
        } else {
            Page<Movie> movies = movieService.findAll(pageable);
            modelAndView = new ModelAndView("movie/list","movies",movies);
        }
        cart.removeMovie(movie.get());
        return modelAndView;
    }
}
