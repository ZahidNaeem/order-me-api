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
    private String code;
    private List<UserModel> users;
}
