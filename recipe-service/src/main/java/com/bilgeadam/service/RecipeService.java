package com.bilgeadam.service;

import com.bilgeadam.dto.request.RecipeSaveRequestDto;
import com.bilgeadam.dto.request.SaveCommentIdRequestDto;
import com.bilgeadam.dto.request.SavePointIdRequestDto;
import com.bilgeadam.entity.Ingredient;
import com.bilgeadam.entity.NutritionalValue;
import com.bilgeadam.entity.Recipe;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.RecipeManagerException;
import com.bilgeadam.mapper.IRecipeMapper;
import com.bilgeadam.repository.IRecipeRepository;
import com.bilgeadam.utility.JwtTokenProvider;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService extends ServiceManager<Recipe,String> {

    private final IRecipeRepository recipeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public RecipeService(IRecipeRepository recipeRepository, JwtTokenProvider jwtTokenProvider) {
        super(recipeRepository);
        this.recipeRepository = recipeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Recipe save(RecipeSaveRequestDto dto){
        try {
            Recipe recipe=IRecipeMapper.INSTANCE.fromRecipeSaveRequestDtoToRecipe(dto);
            return save(recipe);
        }catch (Exception e){
            throw new RecipeManagerException(ErrorType.BAD_REQUEST);
        }
    }
    public List<Recipe> sortByCategory(String token, String categoryId){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new RecipeManagerException(ErrorType.INVALID_TOKEN);
        }
        List<Recipe> recipeList=recipeRepository.findByCategoryId(categoryId);
        return recipeList;
    }
    public List<Recipe> sortByRecipeName(String token, String recipeName){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new RecipeManagerException(ErrorType.INVALID_TOKEN);
        }
        List<Recipe> recipeList=recipeRepository.findByRecipeNameIgnoreCase(recipeName);
        return recipeList;
    }
    public List<Recipe> sortByIngredientName(String token, String ingredientName){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new RecipeManagerException(ErrorType.INVALID_TOKEN);
        }
        List<Recipe> recipeList=recipeRepository.findByIngredientIngredientName(ingredientName);
        return recipeList;
    }
    public List<Recipe> sortByCalori(String token){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new RecipeManagerException(ErrorType.INVALID_TOKEN);
        }
        List<Recipe> recipeList=recipeRepository.findByOrderByNutritionalValueKaloriDesc();
        return recipeList;
    }

    public Boolean saveCommentId(SaveCommentIdRequestDto dto) {
        System.out.println(dto+"recipeservice");
        Optional<Recipe> recipe=recipeRepository.findById(dto.getRecipeId());
        System.out.println(recipe+"recipeservice veritabanındancekilenveri");
        if (recipe.isPresent()) {
            System.out.println("deneme");
            recipe.get().getCommentId().add("2");
            recipe.get().getCommentId().add(dto.getCommentId());
            System.out.println("deneme2");
            update(recipe.get());
            System.out.println(recipe+" id kayıt edilmesi lazım");
            return true;
        }
        throw new RecipeManagerException(ErrorType.USER_NOT_FOUND);
    }
    public Boolean savePointId(SavePointIdRequestDto dto) {
        System.out.println(dto+"recipeservice");
        Optional<Recipe> recipe=recipeRepository.findById(dto.getRecipeId());
        System.out.println(recipe+"recipeservice veritabanındancekilenveri");
        if (recipe.isPresent()) {
            System.out.println("deneme");
            recipe.get().getCommentId().add("2");
            recipe.get().getCommentId().add(dto.getPointId());
            System.out.println("deneme2");
            update(recipe.get());
            System.out.println(recipe+" id kayıt edilmesi lazım");
            return true;
        }
        throw new RecipeManagerException(ErrorType.USER_NOT_FOUND);
    }
}
