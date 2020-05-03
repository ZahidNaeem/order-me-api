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
public class BankModel {
    private Long id;
    private String bank;
    private String logo;
    private List<UserModel> users;
}
