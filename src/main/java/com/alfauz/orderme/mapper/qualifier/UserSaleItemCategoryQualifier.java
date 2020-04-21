package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.ItemCategoryEntity;
import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.service.ItemCategoryService;
import com.alfauz.orderme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSaleItemCategoryQualifier {

    private final ItemCategoryService itemCategoryService;
    private final UserService userService;

    @Named(value = "itemCategoryModelToEntity")
    public ItemCategoryEntity itemCategory(final String id) {
        return  id != null ? itemCategoryService.findById(id) : null;
    }

    @Named(value = "itemCategoryEntityToModel")
    public String itemCategory(final ItemCategoryEntity entity) {
        return entity.getItemCategory();
    }

    @Named("userModelToEntity")
    public UserEntity user(final Long id) {
        return id != null ? userService.findById(id) : null;
    }

    @Named("userEntityToModel")
    public Long user(final UserEntity userEntity) {
        return userEntity.getId();
    }
}
