package com.bilgeadam.repository;


import com.bilgeadam.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepository extends MongoRepository<Recipe,String> {

    List<Recipe> findByCategoryId(String categoryId);
    List<Recipe> findByRecipeNameIgnoreCase(String recipeName);
    List<Recipe> findByIngredientIngredientName(String ingredientName);
    List<Recipe> findByOrderByNutritionalValueKaloriDesc();
}
