package az.edu.turing.mapper;

import az.edu.turing.domain.entity.FlightEntity;

public interface EntityMapper<E, D> {

    E toEntity(D d);

    D toDto(E e);
}
