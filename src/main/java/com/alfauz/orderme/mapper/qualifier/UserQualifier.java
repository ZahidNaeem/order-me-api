package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.entity.UserMainCategoryEntity;
import com.alfauz.orderme.mapper.RoleMapper;
import com.alfauz.orderme.mapper.UserMainCategoryMapper;
import com.alfauz.orderme.model.RoleModel;
import com.alfauz.orderme.model.UserMainCategoryModel;
import com.alfauz.orderme.service.CountryCodeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Data
@RequiredArgsConstructor
public class UserQualifier {

    private final RoleMapper roleMapper;
    private final UserMainCategoryMapper useruserMainCategoryMapper;
    private final CountryCodeService countryCodeService;

    @Named("rolesMTE")
    public Set<RoleEntity> rolesMTE(final Set<RoleModel> models) {
        return roleMapper.toEntities(models);
    }

    @Named("rolesETM")
    public Set<RoleModel> rolesETM(final Set<RoleEntity> entities) {
        return roleMapper.toModels(entities);
    }

    @Named("userMainCategoriesMTE")
    public List<UserMainCategoryEntity> userMainCategoriesMTE(final List<UserMainCategoryModel> models) {
        return useruserMainCategoryMapper.toEntities(models);
    }

    @Named("userMainCategoriesETM")
    public List<UserMainCategoryModel> userMainCategoriesETM(final List<UserMainCategoryEntity> entities) {
        return useruserMainCategoryMapper.toModels(entities);
    }

    @Named("countryCodeMTE")
    public CountryCodeEntity countryCode(final Long id) {
        return countryCodeService.findById(id);
    }

    @Named("countryCodeETM")
    public Long countryCode(final CountryCodeEntity entity) {
        return entity.getId();
    }
}
