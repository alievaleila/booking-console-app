package az.edu.turing.service.inter;

import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;

import java.util.Collection;

public interface FlightService {

    FlightResponse create(FlightRequestDto flight);

    FlightResponse update(FlightRequestDto flight);

    Collection<FlightResponse> getAllFlightResponse();

    FlightResponse getById(long id);

    FlightResponse deleteById(long id);

    FlightResponse searchFlight(FlightRequestDto flightRequestDto);

    boolean bookSeats(long flightId, int seats);

}
