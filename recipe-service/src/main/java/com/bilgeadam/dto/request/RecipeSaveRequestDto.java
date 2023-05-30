package com.bilgeadam.dto.request;

import com.bilgeadam.entity.Ingredient;
import com.bilgeadam.entity.NutritionalValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeSaveRequestDto {
    private String recipeName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String recipeInformation;
    private String picture;
    //private String comment;
    //
    private List<Ingredient> ingredient;
    private NutritionalValue nutritionalValue;
    private String categoryId;
}
