package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.SaveCommentRequestDto;
import com.bilgeadam.dto.response.SaveCommentResponseDto;
import com.bilgeadam.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICommentMapper {

    ICommentMapper INSTANCE= Mappers.getMapper(ICommentMapper.class);

    Comment fromSaveCommentRequestDtoToComment(final SaveCommentRequestDto dto);

    SaveCommentResponseDto toResponseDto(final Comment comment);
}
