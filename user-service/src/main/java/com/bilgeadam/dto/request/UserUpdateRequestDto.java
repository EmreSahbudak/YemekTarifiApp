package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String street;
    private String neighborhood;
    private String county;
    private String city;
    private String apartmentNumber;
    private String avatar;
    private String token;
}
