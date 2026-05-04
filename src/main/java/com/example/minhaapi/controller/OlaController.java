package com.example.minhaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que esta classe é um controlador REST
public class OlaController {

    @GetMapping("/ola") // Define a rota HTTP GET localhost:8080/ola
    public String dizerOla() {
        return "Minha primeira API Spring Boot rodando no VS Code!";
    }
}