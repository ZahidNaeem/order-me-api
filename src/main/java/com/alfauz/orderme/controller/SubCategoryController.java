package com.alfauz.orderme.controller;

import com.alfauz.orderme.mapper.SubCategoryMapper;
import com.alfauz.orderme.model.SubCategoryModel;
import com.alfauz.orderme.payload.response.ApiResponse;
import com.alfauz.orderme.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("subCategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;
    private final SubCategoryMapper subCategoryMapper;

    @GetMapping(path = "all")
    public ResponseEntity<ApiResponse<List<SubCategoryModel>>> findAll() {
        final List<SubCategoryModel> models = subCategoryMapper.toModels(subCategoryService.findAll());
        final Stream<SubCategoryModel> modelStream = models.stream().sorted(Comparator.comparing(SubCategoryModel::getSubCatName));
        final List<SubCategoryModel> sortedModels = modelStream.collect(Collectors.toList());
        return ResponseEntity.ok(
                ApiResponse
                        .<List<SubCategoryModel>>builder()
                        .success(true)
                        .message("findAll response")
                        .entity(sortedModels)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubCategoryModel>>> findAllByMainCategory(@RequestParam("parent_cat") final Long id) {
        final List<SubCategoryModel> models = subCategoryService.findAllByMainCategory(id);
        final Stream<SubCategoryModel> modelStream = models.stream().sorted(Comparator.comparing(SubCategoryModel::getSubCatName));
        final List<SubCategoryModel> sortedModels = modelStream.collect(Collectors.toList());
        return ResponseEntity.ok(
                ApiResponse
                        .<List<SubCategoryModel>>builder()
                        .success(true)
                        .message("findAll response")
                        .entity(sortedModels)
                        .build()
        );
    }
}
