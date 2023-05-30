package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerRegisterAddressRequestDto {

    private Long authId;
    private String street; //sokak
    private String neighborhood; //mahalle
    private String county; //ilçe
    private String city; //il
    private String apartmentNumber; //bina no
}
