package com.bilgeadam.manager;

import com.bilgeadam.dto.request.SaveCommentIdRequestDto;
import com.bilgeadam.dto.request.SavePointIdRequestDto;
import com.bilgeadam.dto.request.SavePointRequestDto;
import com.bilgeadam.dto.response.RecipeResponseDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "comment-recipe",
        url = "http://localhost:8075/api/v1/recipe"
)
public interface IRecipeManager {



    @PostMapping("/save-comment-id")
    public ResponseEntity<Boolean> saveCommentId(@RequestBody SaveCommentIdRequestDto dto);

    @PostMapping("/save-point-id")
    public ResponseEntity<Boolean> savePointId(@RequestBody SavePointIdRequestDto dto);
}
