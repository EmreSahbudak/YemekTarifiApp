package com.bilgeadam.controller;

import com.bilgeadam.dto.request.SaveCommentRequestDto;
import com.bilgeadam.dto.response.SaveCommentResponseDto;
import com.bilgeadam.entity.Comment;
import com.bilgeadam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }
    @PostMapping(SAVE)
    public ResponseEntity<SaveCommentResponseDto> createComment(String token, @RequestBody SaveCommentRequestDto dto){
        return ResponseEntity.ok(commentService.createComment(token, dto));
    }
}
