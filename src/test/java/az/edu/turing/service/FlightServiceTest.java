package az.edu.turing.service;

import az.edu.turing.domain.dao.impl.memory.FlightDaoInMemory;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.exception.FlightNotFoundException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightServiceTest {

    private FlightServiceImpl flightService;
    private FlightDaoInMemory flightDao;
    private FlightMapper flightMapper;

    @BeforeEach
    void setUp() {
        flightDao = new FlightDaoInMemory();
        flightMapper = new FlightMapper();
        flightService = new FlightServiceImpl(flightDao, flightMapper);
        FlightDaoInMemory.flights.clear();
    }

    @Test
    void testCreateFlight() {
        FlightRequestDto flightRequestDto = new FlightRequestDto("New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150, 100);

        FlightResponse flightResponse = flightService.create(flightRequestDto);

        assertNotNull(flightResponse);
        assertEquals("New York", flightResponse.getDeparturePoint());
        assertEquals("London", flightResponse.getDestinationPoint());
        assertEquals(150, flightResponse.getTotalSeats());
        assertEquals(100, flightResponse.getAvailableSeats());
    }

    @Test
    void testCreateFlightAlreadyExists() {
        FlightRequestDto flightRequestDto = new FlightRequestDto("New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150, 100);

        flightService.create(flightRequestDto);

        assertThrows(AlreadyExistsException.class, () -> flightService.create(flightRequestDto));
    }


    @Test
    void testGetFlightByIdNotFound() {
        assertThrows(FlightNotFoundException.class, () -> flightService.getById(999L));
    }


    @Test
    void testUpdateFlight() {
        FlightRequestDto flightRequestDto = new FlightRequestDto("New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);

        flightService.create(flightRequestDto);

        FlightRequestDto updatedFlightRequestDto = new FlightRequestDto
                ("New York", "Paris",
                        LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                        100);

        FlightResponse updatedFlightResponse = flightService.update(updatedFlightRequestDto);

        assertNotNull(updatedFlightResponse);
        assertEquals("New York", updatedFlightResponse.getDeparturePoint());
        assertEquals("Paris", updatedFlightResponse.getDestinationPoint());
    }

    @Test
    void testGetAllFlights() {
        FlightRequestDto flightRequestDto1 = new FlightRequestDto("New York",
                "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);
        FlightRequestDto flightRequestDto2 = new FlightRequestDto("Paris", "Tokyo",
                LocalDateTime.of(2024, 12, 15, 12, 20), 200,
                150);

        flightService.create(flightRequestDto1);
        flightService.create(flightRequestDto2);

        Collection<FlightResponse> flights = flightService.getAllFlightResponse();

        assertNotNull(flights);
        assertEquals(2, flights.size());

        assertTrue(flights.stream().anyMatch(flight -> flight.getDeparturePoint().equals("New York")));
        assertTrue(flights.stream().anyMatch(flight -> flight.getDeparturePoint().equals("Paris")));
    }
}



