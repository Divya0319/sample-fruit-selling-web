package com.sample.fruitsellingweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.fruitsellingweb.commons.CommonApiResponses;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

    @GetMapping("/list")
    @CommonApiResponses
    public String getListOfFruits() {
        return "List of all available fruits";
    }

    @GetMapping("/details")
    @CommonApiResponses
    public String getFruitDetails() {
        return "Details of a specific fruit";
    }

    @PostMapping("/add")
    @CommonApiResponses
    public String addNewFruit() {
        return "New fruit added";
    }

    @PutMapping("/update")
    @CommonApiResponses
    public String updateFruit() {
        return "Fruit updated";
    }

    @DeleteMapping("/delete")
    @CommonApiResponses
    public String deleteFruit() {
        return "Fruit deleted";
    }

    @GetMapping("/search")
    @CommonApiResponses
    public String searchFruits() {
        return "Search results for fruits";
    }

    @PostMapping("/purchase")
    @CommonApiResponses
    public String purchaseFruit() {
        return "Fruit purchased";
    }
}
