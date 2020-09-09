package com.github.overseasbasketballconnection.locations.utils.services;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface SimpleServiceInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>> {

    CrudRepositoryType getRepository();

    EntityType update(IdType id, EntityType newEntityData);

    default EntityType create(EntityType entity) {
        getRepository().save(entity);
        return entity;
    }

    default EntityType delete(IdType id) {
        return delete(findById(id));
    }

    default EntityType delete(EntityType entity) {
        getRepository().delete(entity);
        return entity;
    }

    default EntityType findById(IdType id) {
        return getRepository().findById(id).get();
    }

    default List<EntityType> findAll() {
        List<EntityType> list = new ArrayList<>();
        getRepository().findAll().forEach(list::add);
        return list;
    }

    default List<EntityType> findAllWhere(Predicate<EntityType> filterClause) {
        return findAll()
                .stream()
                .filter(filterClause)
                .collect(Collectors.toList());
    }
}
