package com.isato.gitoryouri.controller;

import com.isato.gitoryouri.model.Category;
import com.isato.gitoryouri.repository.CategoryRepository;
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
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        return categoryRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Category> post(@Valid @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> put(@Valid @RequestBody Category category){
        return categoryRepository.findById(category.getId()).map(response -> ResponseEntity.status(HttpStatus.OK)
                .body(categoryRepository.save(category))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(id);
    }


}
