package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RecipeSaveRequestDto;
import com.bilgeadam.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IRecipeMapper {

    IRecipeMapper INSTANCE= Mappers.getMapper(IRecipeMapper.class);

    Recipe fromRecipeSaveRequestDtoToRecipe(final RecipeSaveRequestDto dto);
}
