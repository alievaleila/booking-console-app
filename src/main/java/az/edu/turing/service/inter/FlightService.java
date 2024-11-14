package az.edu.turing.service.inter;

import az.edu.turing.domain.entity.FlightEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FlightService {

    FlightEntity create(FlightEntity flight);

    FlightEntity update(FlightEntity flight);

    Collection<FlightEntity> getAll();

    FlightEntity getById(UUID id);

    FlightEntity getByFlightNumber(int flightNumber);

    FlightEntity deleteById(UUID id);

    boolean existsById(UUID id);

    boolean bookSeats(UUID flightId, int seats);

    Collection<FlightEntity> getAllInNext24Hours();
}
