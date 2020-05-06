package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.MainCategoryEntity;
import com.alfauz.orderme.service.MainCategoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class SubCategoryQualifier {

    private final MainCategoryService mainCategoryService;

    public MainCategoryEntity mainCategory(final Long id) {
        return id != null ? mainCategoryService.findById(id) : null;
    }

    public Long mainCategory(final MainCategoryEntity entity) {
        return entity != null ? entity.getId() : null;
    }

}
