package com.isato.gitoryouri.controller;

import com.isato.gitoryouri.model.Recipe;
import com.isato.gitoryouri.repository.RecipeRepository;
import com.isato.gitoryouri.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;


    @GetMapping
    public ResponseEntity<List<Recipe>> getAll(){
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Long id){
        return recipeRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Recipe> post(@Valid @RequestBody Recipe recipe){

        ResponseEntity<Recipe> response = recipeService.createRecipe(recipe)
            .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
            
        recipeService.createRecipeRelations(response.getBody());
        
        return response;
    }

    @PutMapping
    public ResponseEntity<Recipe> put(@Valid @RequestBody Recipe recipe){
        return recipeRepository.findById(recipe.getId()).map(response -> ResponseEntity.status(HttpStatus.OK)
                .body(recipeRepository.save(recipe))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if(recipe.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        recipeRepository.deleteById(id);
    }
}
