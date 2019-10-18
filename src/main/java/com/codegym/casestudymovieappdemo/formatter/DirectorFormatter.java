package com.codegym.casestudymovieappdemo.formatter;

import com.codegym.casestudymovieappdemo.model.Director;
import com.codegym.casestudymovieappdemo.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class DirectorFormatter implements Formatter<Director> {

    private DirectorService directorService;

    @Autowired
    public DirectorFormatter(DirectorService directorService) {
        this.directorService = directorService;
    }

    @Override
    public Director parse(String text, Locale locale) throws ParseException {
        return directorService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Director object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
