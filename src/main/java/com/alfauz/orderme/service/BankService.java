package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.BankEntity;

import java.util.List;

public interface BankService {

    List<BankEntity> findAll();

    BankEntity findById(final Long id);
}
