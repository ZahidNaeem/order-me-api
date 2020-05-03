package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.BankEntity;
import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.entity.UserMainCategoryEntity;
import com.alfauz.orderme.mapper.RoleMapper;
import com.alfauz.orderme.mapper.UserMainCategoryMapper;
import com.alfauz.orderme.model.RoleModel;
import com.alfauz.orderme.model.UserMainCategoryModel;
import com.alfauz.orderme.service.BankService;
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
    private final UserMainCategoryMapper userMainCategoryMapper;
    private final CountryCodeService countryCodeService;
    private final BankService bankService;

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
        return userMainCategoryMapper.toEntities(models);
    }

    @Named("userMainCategoriesETM")
    public List<UserMainCategoryModel> userMainCategoriesETM(final List<UserMainCategoryEntity> entities) {
        return userMainCategoryMapper.toModels(entities);
    }

    @Named("countryCodeMTE")
    public CountryCodeEntity countryCode(final Long id) {
        return id != null ? countryCodeService.findById(id) : null;
    }

    @Named("countryCodeETM")
    public Long countryCode(final CountryCodeEntity entity) {
        return entity != null ? entity.getId() : null;
    }

    @Named("bankMTE")
    public BankEntity bank(final Long id) {
        return id != null ? bankService.findById(id) : null;
    }

    @Named("bankETM")
    public Long bank(final BankEntity entity) {
        return entity != null ? entity.getId() : null;
    }
}
