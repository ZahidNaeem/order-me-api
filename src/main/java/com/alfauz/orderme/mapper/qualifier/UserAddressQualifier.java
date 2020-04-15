package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.enumeration.AddressType;
import com.alfauz.orderme.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class UserAddressQualifier {

    private final UserService userService;

    @Named("addressTypeModelToEntity")
    public AddressType addressType(final String addressType) {
        return AddressType.fromValue(addressType);
    }

    @Named("addressTypeEntityToModel")
    public String addressType(final AddressType addressType) {
        return addressType.getValue();
    }

    @Named("userModelToEntity")
    public UserEntity user(final Long user) {
        return userService.findById(user);
    }

    @Named("userEntityToModel")
    public Long user(final UserEntity userEntity) {
        return userEntity.getId();
    }
}
