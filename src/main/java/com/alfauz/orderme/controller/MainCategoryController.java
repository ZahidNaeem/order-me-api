package com.alfauz.orderme.controller;

import com.alfauz.orderme.mapper.MainCategoryMapper;
import com.alfauz.orderme.model.MainCategoryModel;
import com.alfauz.orderme.payload.response.ApiResponse;
import com.alfauz.orderme.service.MainCategoryService;
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
@RequestMapping("mainCategories")
@RequiredArgsConstructor
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;
    private final MainCategoryMapper mainCategoryMapper;
    private List<MainCategoryModel> models = new ArrayList<>();

    @PostConstruct
    public void init() {
        models = mainCategoryMapper.toModels(mainCategoryService.findAll());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MainCategoryModel>>> findAll() {
        final Stream<MainCategoryModel> modelStream = models.stream().sorted(Comparator.comparing(MainCategoryModel::getMainCatName));
        final List<MainCategoryModel> sortedModels = modelStream.collect(Collectors.toList());
        return ResponseEntity.ok(
                ApiResponse
                        .<List<MainCategoryModel>>builder()
                        .success(true)
                        .message("findAll response")
                        .entity(sortedModels)
                        .build()
        );
    }
}
