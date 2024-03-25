package com.orion.mdd.mappers;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDtos(List<E> entities);
}
