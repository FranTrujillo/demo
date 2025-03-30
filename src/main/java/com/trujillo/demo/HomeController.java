package com.trujillo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Home Controller", description = "API for Home Page")
public class HomeController {

    @GetMapping("/")
    @Operation(summary = "Get Home Page", description = "Returns a welcome message for the home page.")
    public String home() {
        return "Welcome to the Home Page!";
    }
}