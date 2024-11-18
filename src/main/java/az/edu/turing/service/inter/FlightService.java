package az.edu.turing.service.inter;

import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface FlightService {

    FlightResponse create(FlightRequestDto flight);

    FlightResponse update(FlightRequestDto flight);

    Collection<FlightResponse> getAllFlightResponse();

    FlightResponse getById(long id);

    FlightResponse deleteById(long id);

    List<FlightResponse> searchFlights(String destination, LocalDate date, int requiredSeats);

    boolean bookSeats(long flightId, int seats);

}
