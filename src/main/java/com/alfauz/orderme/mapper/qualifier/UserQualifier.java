package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.entity.UserAddressEntity;
import com.alfauz.orderme.enumeration.ActivationStatus;
import com.alfauz.orderme.enumeration.UserType;
import com.alfauz.orderme.mapper.RoleMapper;
import com.alfauz.orderme.mapper.UserAddressMapper;
import com.alfauz.orderme.model.RoleModel;
import com.alfauz.orderme.model.UserAddressModel;
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
    private final UserAddressMapper userAddressMapper;
    private final CountryCodeService countryCodeService;

    @Named("rolesModelsToEntities")
    public Set<RoleEntity> rolesMTE(final Set<RoleModel> models) {
        return roleMapper.toEntities(models);
    }

    @Named("rolesEntitiesToModels")
    public Set<RoleModel> rolesETM(final Set<RoleEntity> entities) {
        return roleMapper.toModels(entities);
    }

    @Named("userAddressesModelsToEntities")
    public List<UserAddressEntity> userAddressesMTE(final List<UserAddressModel> models) {
        return userAddressMapper.toEntities(models);
    }

    @Named("userAddressesEntitiesToModels")
    public List<UserAddressModel> userAddressesETM(final List<UserAddressEntity> entities) {
        return userAddressMapper.toModels(entities);
    }

    @Named("userTypeModelToEntity")
    public UserType userType(final String value) {
        return UserType.fromValue(value);
    }

    @Named("userTypeEntityToModel")
    public String userType(final UserType userType) {
        return userType.getValue();
    }

    @Named("countryCodeModelToEntity")
    public CountryCodeEntity countryCode(final Long id) {
        return countryCodeService.findById(id);
    }

    @Named("countryCodeEntityToModel")
    public Long countryCode(final CountryCodeEntity entity) {
        return entity.getId();
    }

    @Named("activationStatusModelToEntity")
    public ActivationStatus activationStatus(final String value) {
        return ActivationStatus.fromValue(value);
    }

    @Named("activationStatusEntityToModel")
    public String activationStatus(final ActivationStatus activationStatus) {
        return activationStatus.getValue();
    }
}
