package az.edu.turing.service;

import az.edu.turing.domain.dao.impl.memory.BookingDaoInMemory;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.BookingNotFoundException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceTest {
    private BookingServiceImpl bookingService;
    private BookingDaoInMemory bookingDao;
    private BookingMapper bookingMapper;
    private FlightEntity flightEntity;
    private PassengerEntity passengerEntity;

    @BeforeEach
    void setUp() {
        bookingDao = new BookingDaoInMemory();
        bookingMapper = new BookingMapper();
        bookingService = new BookingServiceImpl(bookingDao, bookingMapper);


        flightEntity = new FlightEntity("Kiev", "Baku", null, 100, 100);
        passengerEntity = new PassengerEntity("John", "Doe");
        BookingDaoInMemory.BOOKINGS.clear();
    }

    @Test
    void testCreateBooking() {

        List<PassengerEntity> passengers = Collections.singletonList(passengerEntity);
        BookingRequestDto bookingRequestDto = new BookingRequestDto(flightEntity, passengers, true);

        BookingResponseDto bookingResponseDto = bookingService.createBooking(bookingRequestDto);

        assertNotNull(bookingResponseDto);
        assertEquals(flightEntity, bookingResponseDto.getFlight());
        assertEquals(1, bookingResponseDto.getPassengers().size());
        assertTrue(bookingResponseDto.isActive());
    }

    @Test
    void testBookingNotFoundExceptionOnDelete() {

        long invalidBookingId = 999L;

        assertThrows(BookingNotFoundException.class, () -> bookingService.deleteBooking(invalidBookingId));
    }

    @Test
    void testBookingNotFoundExceptionOnGetDetails() {
        long invalidBookingId = 999L;

        assertThrows(BookingNotFoundException.class, () -> bookingService.getBookingDetails(invalidBookingId));
    }

    @Test
    void testFindNoFlightsByNameAndSurname() {
        List<String> flights = bookingService.findMyFlightsByNameAndSurname("Nonexistent", "Person");


        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }
}
