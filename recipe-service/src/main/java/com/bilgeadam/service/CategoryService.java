package com.bilgeadam.service;

import com.bilgeadam.dto.request.CategorySaveRequestDto;
import com.bilgeadam.entity.Category;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.RecipeManagerException;
import com.bilgeadam.repository.ICategoryRepository;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends ServiceManager<Category,String> {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public Boolean save(CategorySaveRequestDto dto){
        try{
            Category category=Category.builder()
                    .categoryName(dto.getCategoryName())
                    .build();
            save(category);
            return true;
        }catch (Exception e){
            throw new RecipeManagerException(ErrorType.BAD_REQUEST);
        }
    }
}
