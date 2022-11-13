package com.isato.gitoryouri.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User extends DomainEntity{

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @URL
    private String photo_url;

    @Size(min = 3, max = 255, message = "About must have at least 3 characters and maximum 255")
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
