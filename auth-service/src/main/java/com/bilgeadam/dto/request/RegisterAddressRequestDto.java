package com.bilgeadam.dto.request;

import com.bilgeadam.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterAddressRequestDto {
    @NotBlank(message = "Token boş bırakmayınız.")
    private String token;
    @NotBlank(message = "street boş bırakmayınız.")
    private String street; //sokak
    @NotBlank(message = "neighborhood boş bırakmayınız.")
    private String neighborhood; //mahalle
    @NotBlank(message = "county boş bırakmayınız.")
    private String county; //ilçe
    @NotBlank(message = "city boş bırakmayınız.")
    private String city; //il
    @NotBlank(message = "country boş bırakmayınız.")
    private String country; //ülke
    @NotBlank(message = "apartmentNumber boş bırakmayınız.")
    private String apartmentNumber; //bina no
    @NotBlank(message = "houseNumber boş bırakmayınız.")
    private String houseNumber; //daire no
    @NotBlank(message = "ZipCode boş bırakmayınız.")
    private String zipCode; //posta kodu

}
