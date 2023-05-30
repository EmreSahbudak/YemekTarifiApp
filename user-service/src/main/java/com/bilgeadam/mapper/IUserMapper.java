package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.ManagerRegisterAddressRequestDto;
import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User fromCreateDtoToAuth(final NewCreateUserRequestDto dto);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //User fromManagerAddressRequestDtoToAuth(final ManagerRegisterAddressRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUpdateDtoToAuth(final UserUpdateRequestDto dto, @MappingTarget User user);

    //@Mapping(source = "id", target = "userId")
    UserProfileResponseDto fromUserProfileToResponseDto(final User user);
}
