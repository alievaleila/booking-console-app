package az.edu.turing.service.inter;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightRequest;
import az.edu.turing.model.dto.response.FlightResponse;

import java.util.Collection;

public interface FlightService {

    FlightEntity create(FlightEntity flight);

    FlightEntity update(FlightEntity flight);

    Collection<FlightEntity> getAll();

    FlightEntity getById(long id);

    FlightEntity deleteById(long id);

    boolean existsById(long id);

    boolean bookSeats(long flightId, int seats);

    Collection<FlightEntity> getAllInNext24Hours();

    Collection<FlightEntity> searchFlights(FlightRequest request);
}
