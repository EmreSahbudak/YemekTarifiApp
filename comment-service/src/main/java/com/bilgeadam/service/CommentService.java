package com.bilgeadam.service;

import com.bilgeadam.dto.request.SaveCommentIdRequestDto;
import com.bilgeadam.dto.request.SaveCommentRequestDto;
import com.bilgeadam.dto.response.SaveCommentResponseDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.entity.Comment;
import com.bilgeadam.exception.CommentManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IRecipeManager;
import com.bilgeadam.manager.IUserProfileManager;
import com.bilgeadam.mapper.ICommentMapper;
import com.bilgeadam.repository.ICommentRepository;
import com.bilgeadam.utility.JwtTokenProvider;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment,String> {

    private final ICommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserProfileManager userProfileManager;
    private final IRecipeManager recipeManager;

    public CommentService(ICommentRepository commentRepository, JwtTokenProvider jwtTokenProvider, IUserProfileManager userProfileManager, IRecipeManager recipeManager) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userProfileManager = userProfileManager;
        this.recipeManager = recipeManager;
    }

    public SaveCommentResponseDto createComment(String token, SaveCommentRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        }
        UserProfileResponseDto userProfile = userProfileManager.findByAuthId(authId.get()).getBody();
        System.out.println(userProfile);
        Comment comment=ICommentMapper.INSTANCE.fromSaveCommentRequestDtoToComment(dto);
        comment.setUsername(userProfile.getUsername());
        save(comment);
        SaveCommentIdRequestDto commentIdRequestDto=SaveCommentIdRequestDto.builder()
                .commentId(comment.getId())
                .recipeId(dto.getRecipeId())
                .build();
        System.out.println(commentIdRequestDto+"commentIdRequestDto");
        recipeManager.saveCommentId(commentIdRequestDto);
        System.out.println(commentIdRequestDto+"commentIdRequestDto");
        System.out.println(comment+"comment");
        return ICommentMapper.INSTANCE.toResponseDto(comment);
    }
}
