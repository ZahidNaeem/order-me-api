package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.repo.CountryCodeRepo;
import com.alfauz.orderme.service.CountryCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryCodeServiceImpl implements CountryCodeService {

    private final CountryCodeRepo countryCodeRepo;

    @Override
    public List<CountryCodeEntity> findAll() {
        return countryCodeRepo.findAll();
    }

    @Override
    public CountryCodeEntity findById(final Long id) {
        return countryCodeRepo.findById(id)
                .orElse(null);
    }
}
