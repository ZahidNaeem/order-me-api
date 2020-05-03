package com.alfauz.orderme.controller;

import com.alfauz.orderme.mapper.BankMapper;
import com.alfauz.orderme.model.BankModel;
import com.alfauz.orderme.payload.response.ApiResponse;
import com.alfauz.orderme.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService countryCodeService;
    private final BankMapper countryCodeMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BankModel>>> findAll() {
        final List<BankModel> models = countryCodeMapper.toModels(countryCodeService.findAll());
        final Stream<BankModel> modelStream = models.stream().sorted(Comparator.comparing(BankModel::getId));
        final List<BankModel> sortedModels = modelStream.collect(Collectors.toList());
        return ResponseEntity.ok(
                ApiResponse
                        .<List<BankModel>>builder()
                        .success(true)
                        .message("findAll response")
                        .entity(sortedModels)
                        .build()
        );
    }
}
