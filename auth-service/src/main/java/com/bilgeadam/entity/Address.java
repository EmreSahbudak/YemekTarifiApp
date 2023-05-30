package com.bilgeadam.entity;

import com.bilgeadam.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Address extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street; //sokak
    private String neighborhood; //mahalle
    private String county; //ilçe
    private String city; //il
    private String country; //ülke
    private String apartmentNumber; //bina no
    private String houseNumber; //daire no
    private String zipCode; //posta kodu
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role = ERole.USER;
    private Long authId;

}
