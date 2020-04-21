package com.alfauz.orderme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryCodeModel {
    private Long id;
    private String country;
    private String alpha2Code;
    private String alpha3Code;
    private String capitalCity;
    private String dialingCode;
    private List<UserModel> users;
}
