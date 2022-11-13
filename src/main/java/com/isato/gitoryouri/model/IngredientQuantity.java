package com.isato.gitoryouri.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "IngredientQuantity")
public class IngredientQuantity extends DomainEntity{

    @NotBlank
    private String quantity;

    @NotBlank
    private String measurement;

    @ManyToOne
    @JsonIgnoreProperties("ingredients")
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JsonIgnoreProperties("recipes")
    private Ingredient ingredient;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }


}
