package az.edu.turing.memory;

import az.edu.turing.domain.dao.impl.memory.FlightDaoInMemory;
import az.edu.turing.domain.entity.FlightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlightDaoInMemoryTest {

    private FlightDaoInMemory flightDao;

    @BeforeEach
    void setUp() {
        flightDao = new FlightDaoInMemory();
    }

    @Test
    void testCreateFlight() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        flightDao.create(flight);

        assertTrue(flightDao.existsById(flight.getId()));
    }

    @Test
    void testGetFlightById() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        flightDao.create(flight);

        Optional<FlightEntity> foundFlight = flightDao.getById(flight.getId());
        assertTrue(foundFlight.isPresent());
        assertEquals(flight, foundFlight.get());
    }

    @Test
    void testUpdateFlight() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        flightDao.create(flight);

        flight.setAvailableSeats(40);
        flightDao.update(flight);

        Optional<FlightEntity> updatedFlight = flightDao.getById(flight.getId());
        assertTrue(updatedFlight.isPresent());
        assertEquals(40, updatedFlight.get().getAvailableSeats());
    }

    @Test
    void testDeleteFlight() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        flightDao.create(flight);

        FlightEntity deletedFlight = flightDao.deleteById(flight.getId());
        assertNotNull(deletedFlight);
        assertFalse(flightDao.existsById(flight.getId()));
    }
}