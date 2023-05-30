package com.bilgeadam.mapper;


import com.bilgeadam.dto.request.SavePointRequestDto;

import com.bilgeadam.entity.Point;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPointMapper {

    IPointMapper INSTANCE= Mappers.getMapper(IPointMapper.class);

    Point fromSaveCommentRequestDtoToComment(final SavePointRequestDto dto);

}
