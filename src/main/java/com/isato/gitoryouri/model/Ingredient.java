package com.isato.gitoryouri.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_ingredient")
public class Ingredient extends DomainEntity{

    public Ingredient() {
    }

    public Ingredient(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 255, message = "Name must have at least 3 characters")
    private String name;

    @NotBlank(message = "Description is required!")
    @Size(min = 3, max = 255, message = "Description must have at least 3 characters")
    private String description;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("ingredient")
    private List<IngredientQuantity> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IngredientQuantity> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<IngredientQuantity> recipes) {
        this.recipes = recipes;
    }
}
