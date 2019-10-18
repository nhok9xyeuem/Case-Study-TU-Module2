package com.codegym.casestudymovieappdemo.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    private String actor;

    @Min(1)
    private int price;

    private String releaseTime;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    public Movie(){}

    public Movie(String name, String actor, int price, String releaseTime){
        this.name = name;
        this.actor = actor;
        this.price = price;
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString(){
        return String.format("Movie[id=%d, name='%s' actor='%s', price=%d, releaseTime='%s']", id, name, actor, price, releaseTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}

