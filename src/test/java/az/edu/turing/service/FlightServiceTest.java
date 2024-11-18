package az.edu.turing.service;

import az.edu.turing.domain.dao.impl.memory.FlightDaoInMemory;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightServiceTest {
    private FlightServiceImpl flightService;
    private FlightDaoInMemory flightDaoInMemory;
    private FlightMapper flightMapper;

    @BeforeEach
    void setUp() {
        flightDaoInMemory = new FlightDaoInMemory();
        flightMapper = new FlightMapper();
        flightService = new FlightServiceImpl(flightDaoInMemory, flightMapper);
    }

    @Test
    void testCreateFlight() {
        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                null, 150, 100);

        FlightResponse flightResponse = flightService.create(flightRequestDto);

        assertNotNull(flightResponse);

        assertEquals("New York", flightResponse.getDeparturePoint());
        assertEquals("London", flightResponse.getDestinationPoint());
        assertEquals(null, flightResponse.getDepartureTime());
        assertEquals(150, flightResponse.getTotalSeats());
        assertEquals(100, flightResponse.getAvailableSeats());

        assertTrue(flightDaoInMemory.getAll().size() > 0);
    }

    @Test
    void testCreateFlightAlreadyExists() {
        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30)
                , 150, 100);

        flightService.create(flightRequestDto);


        FlightRequestDto duplicateFlightRequestDto = new FlightRequestDto(1L, "New York",
                "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);


        assertThrows(AlreadyExistsException.class, () -> flightService.create(duplicateFlightRequestDto));
    }

    @Test
    void testUpdateFlight() {
        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);

        flightService.create(flightRequestDto);

        FlightRequestDto updatedFlightRequestDto = new FlightRequestDto
                (1L, "New York", "Paris",
                        LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                        100);

        FlightResponse updatedFlightResponse = flightService.update(updatedFlightRequestDto);

        assertNotNull(updatedFlightResponse);
        assertEquals("New York", updatedFlightResponse.getDeparturePoint());
        assertEquals("Paris", updatedFlightResponse.getDestinationPoint());
    }

    @Test
    void testGetAllFlights() {
        FlightRequestDto flightRequestDto1 = new FlightRequestDto(1L, "New York",
                "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);
        FlightRequestDto flightRequestDto2 = new FlightRequestDto(2L, "Paris", "Tokyo",
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

    @Test
    void testGetFlightById() {
        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);

        flightService.create(flightRequestDto);

        FlightResponse flightResponse = flightService.getById(1L);

        assertNotNull(flightResponse);
        assertEquals("New York", flightResponse.getDeparturePoint());
        assertEquals("London", flightResponse.getDestinationPoint());
    }

    @Test
    void testDeleteFlightById() {
        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);

        flightService.create(flightRequestDto);

        FlightResponse deletedFlight = flightService.deleteById(1L);

        assertNotNull(deletedFlight);
        assertEquals("New York", deletedFlight.getDeparturePoint());
        assertEquals("London", deletedFlight.getDestinationPoint());

        assertTrue(flightDaoInMemory.getAll().isEmpty());
    }

    @Test
    void testBookSeats() {

        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);

        flightService.create(flightRequestDto);

        boolean bookingSuccess = flightService.bookSeats(1L, 10);

        assertTrue(bookingSuccess);

        FlightResponse flightResponse = flightService.getById(1L);
        assertEquals(90, flightResponse.getAvailableSeats());
    }

    @Test
    void testBookSeats_InsufficientSeats() {

        FlightRequestDto flightRequestDto = new FlightRequestDto(1L, "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30), 150,
                100);

        flightService.create(flightRequestDto);

        boolean bookingSuccess = flightService.bookSeats(1L, 200);

        assertFalse(bookingSuccess);

        FlightResponse flightResponse = flightService.getById(1L);
        assertEquals(100, flightResponse.getAvailableSeats());
    }
}


