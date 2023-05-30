package com.bilgeadam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Document
public class Recipe extends Base{
    @Id
    private String recipeId;
    private String recipeName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String recipeInformation;
    private String picture;
    private List<String> commentId;
    private List<String> pointId;
    //
    private List<Ingredient> ingredient;
    private NutritionalValue nutritionalValue;
    private String categoryId;
}
