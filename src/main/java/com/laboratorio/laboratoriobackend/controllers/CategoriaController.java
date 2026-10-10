package com.laboratorio.laboratoriobackend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    @PostMapping("/")
    public String Create(){
        return "Create";
    }
    @GetMapping("/read")
    public String Read(){
        return "Read";
    }
    @PutMapping("/update")
    public String Update(){
        return "Update";
    }
    @DeleteMapping("/delete")
    public String Delete(){
        return "Delete";
    }
}
