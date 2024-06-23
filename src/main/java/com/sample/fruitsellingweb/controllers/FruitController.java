package com.sample.fruitsellingweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

    @GetMapping("/list")
    public String getListOfFruits() {
        return "List of all available fruits";
    }

    @GetMapping("/details")
    public String getFruitDetails() {
        return "Details of a specific fruit";
    }

    @PostMapping("/add")
    public String addNewFruit() {
        return "New fruit added";
    }

    @PutMapping("/update")
    public String updateFruit() {
        return "Fruit updated";
    }

    @DeleteMapping("/delete")
    public String deleteFruit() {
        return "Fruit deleted";
    }

    @GetMapping("/search")
    public String searchFruits() {
        return "Search results for fruits";
    }

    @PostMapping("/purchase")
    public String purchaseFruit() {
        return "Fruit purchased";
    }
}
