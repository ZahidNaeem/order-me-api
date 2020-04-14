package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.User;
import com.alfauz.orderme.mapper.qualifier.UserQualifier;
import com.alfauz.orderme.model.UserModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {UserQualifier.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {

    UserModel toModel(final User user);

    User toEntity(final UserModel model);

    default List<UserModel> toModels(final List<User> Users) {
        if (CollectionUtils.isEmpty(Users)) {
            return new ArrayList<>();
        }
        final List<UserModel> models = new ArrayList<>();
        Users.forEach(User -> {
            models.add(this.toModel(User));
        });
        return models;
    }

    default List<User> toEntities(final List<UserModel> models) {
        if (CollectionUtils.isEmpty(models)) {
            return new ArrayList<>();
        }
        final List<User> Users = new ArrayList<>();
        models.forEach(model -> {
            Users.add(this.toEntity(model));
        });
        return Users;
    }
}
