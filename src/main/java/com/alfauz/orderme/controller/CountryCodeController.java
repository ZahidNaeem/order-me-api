package com.alfauz.orderme.controller;

import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.mapper.CountryCodeMapper;
import com.alfauz.orderme.model.CountryCodeModel;
import com.alfauz.orderme.payload.response.ApiResponse;
import com.alfauz.orderme.service.CountryCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("countryCodes")
@RequiredArgsConstructor
public class CountryCodeController {

    private final CountryCodeService countryCodeService;
    private final CountryCodeMapper countryCodeMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CountryCodeModel>>> findAll() {
        final List<CountryCodeModel> models = countryCodeMapper.toModels(countryCodeService.findAll());
        final Stream<CountryCodeModel> modelStream = models.stream().sorted(Comparator.comparing(CountryCodeModel::getAlpha2Code));
        final List<CountryCodeModel> sortedModels = modelStream.collect(Collectors.toList());
        return ResponseEntity.ok(
                ApiResponse
                        .<List<CountryCodeModel>>builder()
                        .success(true)
                        .message("findAll response")
                        .entity(sortedModels)
                        .build()
        );
    }
}
