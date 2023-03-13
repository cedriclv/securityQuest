package com.wildcodeschool.mySecurityQuest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.mySecurityQuest.models.City;

@RestController
public class TestController {

    public static List<City> cities = new ArrayList<>();

    @GetMapping("/")
    public String allPath() {
        return "Hello all";
    }

    @GetMapping("/avengers/assemble")
    public String championsOnly() {
        return "Avengers... Assemble";
    }

    @GetMapping("/secret-bases")
    public List<City> directorOnly() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Nice"));
        cities.add(new City("Toulouse"));
        cities.add(new City("Angers"));
        cities.add(new City("Orleans"));

        return cities;
    }

}
