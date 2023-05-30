package com.bilgeadam.entity;

import com.bilgeadam.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Document
public class User extends Base{

    @Id
    private String id;
    private Long authId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String street; //sokak
    private String neighborhood; //mahalle
    private String county; //ilçe
    private String city; //il
    private String apartmentNumber; //bina no
    private String avatar;
    @Builder.Default
    private EStatus status = EStatus.PENDING;


}
