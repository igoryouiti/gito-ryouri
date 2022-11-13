package com.isato.gitoryouri.service;


import com.isato.gitoryouri.model.Ingredient;
import com.isato.gitoryouri.model.IngredientQuantity;
import com.isato.gitoryouri.model.Recipe;
import com.isato.gitoryouri.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    private IngredientQuantityRepository ingredientQuantityRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Recipe> createRecipe(Recipe recipe){
        

        return Optional.of(recipeRepository.save(recipe));
    }

    public void createRecipeRelations(Recipe recipe){

        recipe.getIngredients().forEach(ingredient -> ingredientQuantityRepository.save(ingredient));

        recipe.getCategories().forEach(category -> recipeCategoryRepository.save(category));
    }
}
