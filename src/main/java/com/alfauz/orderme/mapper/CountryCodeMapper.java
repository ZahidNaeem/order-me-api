package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.mapper.qualifier.CountryCodeQualifier;
import com.alfauz.orderme.model.CountryCodeModel;
import com.alfauz.orderme.model.UserModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = CountryCodeQualifier.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CountryCodeMapper {

    @Mapping(target = "users", qualifiedByName = "usersMTE")
    CountryCodeEntity toEntity(final CountryCodeModel model);

    @Mapping(target = "users", qualifiedByName = "usersETM")
    CountryCodeModel toModel(final CountryCodeEntity entity);

    default List<CountryCodeEntity> toEntities(final List<CountryCodeModel> models) {
        final List<CountryCodeEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default List<CountryCodeModel> toModels(final List<CountryCodeEntity> entities) {
        final List<CountryCodeModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
