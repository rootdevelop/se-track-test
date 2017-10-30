package com.example.movieapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    protected Movie() {}

    public Movie(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private boolean watched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
