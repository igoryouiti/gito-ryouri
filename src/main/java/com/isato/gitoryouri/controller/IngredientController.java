package com.isato.gitoryouri.controller;

import com.isato.gitoryouri.model.Ingredient;
import com.isato.gitoryouri.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IngredientController {


    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll(){
        return ResponseEntity.ok(ingredientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Long id){
        return ingredientRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Ingredient> post(@Valid @RequestBody Ingredient ingredient){
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientRepository.save(ingredient));
    }

    @PutMapping
    public ResponseEntity<Ingredient> put(@Valid @RequestBody Ingredient ingredient){
        return ingredientRepository.findById(ingredient.getId()).map(response -> ResponseEntity.status(HttpStatus.OK)
                .body(ingredientRepository.save(ingredient))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);

        if(ingredient.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        ingredientRepository.deleteById(id);
    }


}
