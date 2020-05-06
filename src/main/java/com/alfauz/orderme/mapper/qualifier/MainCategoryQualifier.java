package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.SubCategoryEntity;
import com.alfauz.orderme.entity.UserMainCategoryEntity;
import com.alfauz.orderme.mapper.SubCategoryMapper;
import com.alfauz.orderme.mapper.UserMainCategoryMapper;
import com.alfauz.orderme.model.SubCategoryModel;
import com.alfauz.orderme.model.UserMainCategoryModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class MainCategoryQualifier {

    private final UserMainCategoryMapper userMainCategoryMapper;
    private final SubCategoryMapper subCategoryMapper;

    public List<UserMainCategoryEntity> userMainCategoriesMTE(final List<UserMainCategoryModel> models) {
        return userMainCategoryMapper.toEntities(models);
    }

    public List<UserMainCategoryModel> userMainCategoriesETM(final List<UserMainCategoryEntity> entities) {
        return userMainCategoryMapper.toModels(entities);
    }

    public List<SubCategoryEntity> subCategoriesMTE(final List<SubCategoryModel> models) {
        return subCategoryMapper.toEntities(models);
    }

    public List<SubCategoryModel> subCategoriesETM(final List<SubCategoryEntity> entities) {
        return subCategoryMapper.toModels(entities);
    }

}
