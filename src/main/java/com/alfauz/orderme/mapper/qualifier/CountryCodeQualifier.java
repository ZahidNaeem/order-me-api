package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.mapper.UserMapper;
import com.alfauz.orderme.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryCodeQualifier {

    private final UserMapper userMapper;

    @Named(value = "usersMTE")
    public List<UserEntity> usersMTE(final List<UserModel> models) {
        return userMapper.toEntities(models);
    }

    @Named(value = "usersETM")
    public List<UserModel> usersETM(final List<UserEntity> entities) {
        return userMapper.toModels(entities);
    }
}
