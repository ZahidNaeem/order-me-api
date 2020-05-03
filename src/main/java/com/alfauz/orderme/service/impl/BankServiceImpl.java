package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.BankEntity;
import com.alfauz.orderme.repo.BankRepo;
import com.alfauz.orderme.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepo countryCodeRepo;

    @Override
    public List<BankEntity> findAll() {
        return countryCodeRepo.findAll();
    }

    @Override
    public BankEntity findById(final Long id) {
        return countryCodeRepo.findById(id)
                .orElse(null);
    }
}
