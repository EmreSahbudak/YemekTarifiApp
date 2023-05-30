package com.bilgeadam.controller;

import com.bilgeadam.dto.request.RecipeSaveRequestDto;
import com.bilgeadam.dto.request.SaveCommentIdRequestDto;
import com.bilgeadam.dto.request.SavePointIdRequestDto;
import com.bilgeadam.entity.Ingredient;
import com.bilgeadam.entity.Recipe;
import com.bilgeadam.service.RecipeService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static  com.bilgeadam.constant.ApiUrls.*;
@RestController
@RequestMapping(RECIPE)
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(SAVE)
    public ResponseEntity<Recipe> save(@RequestBody RecipeSaveRequestDto dto){
        return ResponseEntity.ok(recipeService.save(dto));
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Recipe>> findAll(){
        return ResponseEntity.ok(recipeService.findAll());
    }
    @GetMapping(SORT_BY_CATEGORY)
    public ResponseEntity<List<Recipe>> sortByCategory(String token,String categoryId){
        return ResponseEntity.ok(recipeService.sortByCategory(token,categoryId));
    }
    @GetMapping(SORT_BY_RECIPE_NAME)
    public ResponseEntity<List<Recipe>> sortByRecipeName(String token,String recipeName){
        return ResponseEntity.ok(recipeService.sortByRecipeName(token,recipeName));
    }
    @GetMapping(SORT_BY_INGREDIENT_NAME)
    public ResponseEntity<List<Recipe>> sortByIngredientName(String token, String ingredientName){
        return ResponseEntity.ok(recipeService.sortByIngredientName(token,ingredientName));
    }
    @GetMapping(SORT_BY_CALORI)
    public ResponseEntity<List<Recipe>> sortByCalori(String token){
        return ResponseEntity.ok(recipeService.sortByCalori(token));
    }

    @PostMapping("/save-comment-id")
    @Hidden
    public ResponseEntity<Boolean> saveCommentId(@RequestBody SaveCommentIdRequestDto dto){
        System.out.println(dto+"recipecontrolller");
        return ResponseEntity.ok(recipeService.saveCommentId(dto));
    }
    @PostMapping("/save-point-id")
    public ResponseEntity<Boolean> savePointId(@RequestBody SavePointIdRequestDto dto){
        System.out.println(dto+"recipecontrolller");
        return ResponseEntity.ok(recipeService.savePointId(dto));
    }
}
