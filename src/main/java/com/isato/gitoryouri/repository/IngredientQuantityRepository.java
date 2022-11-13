package com.isato.gitoryouri.repository;

import com.isato.gitoryouri.model.IngredientQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Long> {
}
