package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.ManagerRegisterAddressRequestDto;
import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.RegisterAddressRequestDto;
import com.bilgeadam.dto.request.RegisterAuthRequestDto;
import com.bilgeadam.dto.response.RegisterAuthResponseDto;
import com.bilgeadam.entity.Address;
import com.bilgeadam.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth fromRegisterDtoToAuth(final RegisterAuthRequestDto dto);

    RegisterAuthResponseDto fromAuthToRegisterResponseDto(final Auth auth);

    NewCreateUserRequestDto fromAuthToNewCreateUserDto(final Auth auth);

    Address fromRegisterAddressDtoToAddress(final RegisterAddressRequestDto dto);

    ManagerRegisterAddressRequestDto fromAddressToManagerRegisterAddress(final Address address);

}
