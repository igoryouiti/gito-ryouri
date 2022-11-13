package com.isato.gitoryouri.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_recipe")
public class Recipe extends DomainEntity{


    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 255, message = "Name must have at least 3 characters")
    private String name;

    @NotBlank(message = "Method is required!")
    @Size(min = 3, max = 255, message = "Method must have at least 3 characters")
    private String method;

    @NotBlank(message = "Description is required!")
    @Size(min = 3, max = 255, message = "Description must have at least 3 characters")
    private String description;

    @URL
    private String photo_url;

    @URL
    private String video_url;

    @ManyToOne
    @JsonIgnoreProperties("recipes")
    private User user;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("recipe")
    private List<RecipeCategory> categories;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("recipe")
    private List<IngredientQuantity> ingredients;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        this.categories = categories;
    }

    public List<IngredientQuantity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientQuantity> ingredients) {
        this.ingredients = ingredients;
    }


}
