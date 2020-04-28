package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.MainCategoryEntity;
import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.service.MainCategoryService;
import com.alfauz.orderme.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class UserMainCategoryQualifier {

    private final MainCategoryService mainCategoryService;
    private final UserService userService;

    @Named("mainCategoryMTE")
    public MainCategoryEntity mainCategory(final Long id) {
        return id != null ? mainCategoryService.findById(id) : null;
    }

    @Named("mainCategoryETM")
    public Long mainCategory(final MainCategoryEntity entity) {
        return entity != null ? entity.getId() : null;
    }

    @Named("userMTE")
    public UserEntity user(final Long id) {
        return id != null ? userService.findById(id) : null;
    }

    @Named("userETM")
    public Long user(final UserEntity entity) {
        return entity != null ? entity.getId() : null;
    }

}
