package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.CountryCodeEntity;

import java.util.List;

public interface CountryCodeService {

    List<CountryCodeEntity> findAll();

    CountryCodeEntity findById(final Long id);
}
