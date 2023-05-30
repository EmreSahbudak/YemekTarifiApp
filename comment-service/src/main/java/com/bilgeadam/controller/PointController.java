package com.bilgeadam.controller;

import com.bilgeadam.dto.request.SaveCommentRequestDto;
import com.bilgeadam.dto.request.SavePointRequestDto;
import com.bilgeadam.dto.response.SaveCommentResponseDto;
import com.bilgeadam.entity.Point;
import com.bilgeadam.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;

@RestController
@RequestMapping(POINT)
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Point>> findAll(){
        return ResponseEntity.ok(pointService.findAll());
    }
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createComment(String token, @RequestBody SavePointRequestDto dto){
        return ResponseEntity.ok(pointService.createPoint(token, dto));
    }
}
