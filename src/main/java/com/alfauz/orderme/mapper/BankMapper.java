package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.BankEntity;
import com.alfauz.orderme.mapper.qualifier.BankQualifier;
import com.alfauz.orderme.model.BankModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = BankQualifier.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BankMapper {

        BankEntity toEntity(final BankModel model);

        BankModel toModel(final BankEntity entity);

    default List<BankEntity> toEntities(final List<BankModel> models) {
        final List<BankEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default List<BankModel> toModels(final List<BankEntity> entities) {
        final List<BankModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
