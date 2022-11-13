package com.isato.gitoryouri.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_category")
public class RecipeCategory extends DomainEntity{

    @ManyToOne
    @JsonIgnoreProperties("recipes")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("categories")
    private Recipe recipe;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


}
