package com.alfauz.orderme.controller;

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
import java.util.List;

@RestController
@RequestMapping("countryCodes")
@RequiredArgsConstructor
public class CountryCodeController {

    private final CountryCodeService countryCodeService;
    private final CountryCodeMapper countryCodeMapper;
    private List<CountryCodeModel> models = new ArrayList<>();

    @PostConstruct
    public void init() {
        models = countryCodeMapper.toModels(countryCodeService.findAll());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CountryCodeModel>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse
                        .<List<CountryCodeModel>>builder()
                        .success(true)
                        .message("findAll response")
                        .entity(models)
                        .build()
        );
    }
}
