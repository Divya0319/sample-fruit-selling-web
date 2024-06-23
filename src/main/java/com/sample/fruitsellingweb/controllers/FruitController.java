package com.sample.fruitsellingweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

	@Operation(summary = "Returns dummy message of fruits list")
    @GetMapping("/list")
    public String getListOfFruits() {
        return "List of all available fruits";
    }

	@Operation(summary = "Returns dummy message of fruit details")
    @GetMapping("/details")
    public String getFruitDetails() {
        return "Details of a specific fruit";
    }

	@Operation(summary = "Returns dummy message of fruit added")
    @PostMapping("/add")
    public String addNewFruit() {
        return "New fruit added";
    }

	@Operation(summary = "Returns dummy message of fruit updated")
    @PutMapping("/update")
    public String updateFruit() {
        return "Fruit updated";
    }

	@Operation(summary = "Returns dummy message of fruit deleted")
    @DeleteMapping("/delete")
    public String deleteFruit() {
        return "Fruit deleted";
    }

	@Operation(summary = "Returns dummy message of fruit search results")
    @GetMapping("/search")
    public String searchFruits() {
        return "Search results for fruits";
    }

	@Operation(summary = "Returns dummy message of fruit purchased")
    @PostMapping("/purchase")
    public String purchaseFruit() {
        return "Fruit purchased";
    }
}
