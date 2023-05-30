package com.bilgeadam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder

public class NutritionalValue { //besin değeri

    private String nutritionalValueName;
    private int kalori;
    private String protein;
    private String karbohydrate;
    private String oil;

}
