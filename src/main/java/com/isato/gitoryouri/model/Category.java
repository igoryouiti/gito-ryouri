package com.isato.gitoryouri.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_category")
public class Category extends DomainEntity{

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 255, message = "Name must have at least 3 characters")
    private String name;

    @NotBlank(message = "Description is required!")
    @Size(min = 3, max = 255, message = "Description must have at least 3 characters")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("category")
    private List<RecipeCategory> recipes;


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

    public List<RecipeCategory> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeCategory> recipes) {
        this.recipes = recipes;
    }
}
