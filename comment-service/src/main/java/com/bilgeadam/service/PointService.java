package com.bilgeadam.service;

import com.bilgeadam.dto.request.SavePointIdRequestDto;
import com.bilgeadam.dto.request.SavePointRequestDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.entity.Point;
import com.bilgeadam.exception.CommentManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IRecipeManager;
import com.bilgeadam.manager.IUserProfileManager;
import com.bilgeadam.mapper.IPointMapper;
import com.bilgeadam.repository.IPointRepository;
import com.bilgeadam.utility.JwtTokenProvider;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointService extends ServiceManager<Point,String> {

    private final IPointRepository pointRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserProfileManager userProfileManager;
    private final IRecipeManager recipeManager;

    public PointService(IPointRepository pointRepository, JwtTokenProvider jwtTokenProvider, IUserProfileManager userProfileManager, IRecipeManager recipeManager) {
        super(pointRepository);
        this.pointRepository = pointRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userProfileManager = userProfileManager;
        this.recipeManager = recipeManager;
    }
    public Boolean createPoint(String token, SavePointRequestDto dto){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        }
        UserProfileResponseDto userProfile = userProfileManager.findByAuthId(authId.get()).getBody();
        System.out.println(userProfile);
        Point point= IPointMapper.INSTANCE.fromSaveCommentRequestDtoToComment(dto);
        save(point);
        SavePointIdRequestDto pointRequestDto= SavePointIdRequestDto.builder()
                .pointId(point.getId())
                .recipeId(dto.getRecipeId())
                .build();
        recipeManager.savePointId(pointRequestDto);
        return true;
    }
    /*
    public SaveCommentResponseDto createComment(String token, SaveCommentRequestDto dto) {
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
    * */
}
