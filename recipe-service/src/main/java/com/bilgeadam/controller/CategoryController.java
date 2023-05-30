package com.bilgeadam.controller;
import static com.bilgeadam.constant.ApiUrls.*;

import com.bilgeadam.dto.request.CategorySaveRequestDto;
import com.bilgeadam.entity.Category;
import com.bilgeadam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(CategorySaveRequestDto dto){
        return ResponseEntity.ok(categoryService.save(dto));
    }
}
