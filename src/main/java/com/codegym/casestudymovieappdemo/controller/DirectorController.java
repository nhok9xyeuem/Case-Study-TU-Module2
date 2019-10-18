package com.codegym.casestudymovieappdemo.controller;

import com.codegym.casestudymovieappdemo.model.Director;
import com.codegym.casestudymovieappdemo.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/list")
    public ModelAndView listDirectors(){
        Iterable<Director> directors = directorService.findAll();
        ModelAndView modelAndView = new ModelAndView("director/list");
        modelAndView.addObject("directors", directors);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("director/create");
        modelAndView.addObject("director", new Director());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveDirector(@ModelAttribute("director") Director director){
        directorService.save(director);

        ModelAndView modelAndView = new ModelAndView("director/create");
        modelAndView.addObject("director", new Director());
        modelAndView.addObject("message", "New director created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Director> director = directorService.findById(id);
        if(director != null) {
            ModelAndView modelAndView = new ModelAndView("director/edit");
            modelAndView.addObject("director", director.get());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updatedirector(@ModelAttribute("director") Director director){
        directorService.save(director);
        ModelAndView modelAndView = new ModelAndView("director/edit");
        modelAndView.addObject("director", director);
        modelAndView.addObject("message", "director updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Director> director = directorService.findById(id);
        if(director != null) {
            ModelAndView modelAndView = new ModelAndView("director/delete");
            modelAndView.addObject("director", director.get());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deletedirector(@ModelAttribute("director") Director director){
        directorService.remove(director.getId());
        return "redirect:list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewMovie(@PathVariable Long id){
        Optional<Director> director = directorService.findById(id);
        ModelAndView modelAndView = new ModelAndView("director/view");
        modelAndView.addObject("director", director.get());
        return modelAndView;
    }
}


