package az.edu.turing.service;

import az.edu.turing.domain.dao.impl.memory.BookingDaoInMemory;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceTest {
    private BookingDaoInMemory bookingDao;
    private BookingMapper bookingMapper;
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        bookingDao = new BookingDaoInMemory();
        bookingMapper = new BookingMapper();
        bookingService = new BookingServiceImpl(bookingDao, bookingMapper);
    }

    @Test
    void testCreateBooking() {

        FlightEntity flight = new FlightEntity(1L, "New York", "London", "22-01-2012 22:22", 100, 100);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("John", "Doe"));
        BookingRequestDto bookingRequestDto = new BookingRequestDto(flight, passengers, true);


        BookingResponseDto responseDto = bookingService.createBooking(bookingRequestDto);
        assertEquals(1L, responseDto.getFlight().getId(), "Flight ID should be correct.");


        assertNotNull(responseDto);
        assertEquals(1, responseDto.getPassengers().size());
        assertTrue(responseDto.isActive());

        assertEquals(99, flight.getAvailableSeats(), "Available seats should decrease after booking.");
    }

    @Test
    void testDeleteBooking() {

        FlightEntity flight = new FlightEntity(1L, "New York", "London",
                "2024-12-01 22:22", 100, 100);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("John", "Doe"));
        BookingRequestDto bookingRequestDto = new BookingRequestDto(flight, passengers, true);

        BookingResponseDto createdBooking = bookingService.createBooking(bookingRequestDto);
        long bookingId = createdBooking.getFlight().getId();


        BookingResponseDto deletedBooking = bookingService.deleteBooking(bookingId);


        assertNotNull(deletedBooking);
        assertFalse(deletedBooking.isActive(), "Booking should be inactive after deletion.");
    }

    @Test
    void testGetBookingDetails() {
        FlightEntity flight = new FlightEntity(1L, "New York", "London",
                "2024-12-01 22:22", 100, 100);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("John", "Doe"));
        BookingRequestDto bookingRequestDto = new BookingRequestDto(flight, passengers, true);

        BookingResponseDto createdBooking = bookingService.createBooking(bookingRequestDto);
        long bookingId = createdBooking.getFlight().getId();

        BookingResponseDto responseDto = bookingService.getBookingDetails(bookingId);


        assertNotNull(responseDto);
        assertEquals(1L, responseDto.getFlight().getId());
        assertEquals(1, responseDto.getPassengers().size(), "There should be one passenger.");
    }

    @Test
    void testUpdateBooking() {

        FlightEntity flight = new FlightEntity(1L, "New York", "London",
                "2024-12-01 22:22", 100, 100);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("John", "Doe"));
        BookingRequestDto bookingRequestDto = new BookingRequestDto(flight, passengers, true);


        BookingResponseDto createdBooking = bookingService.createBooking(bookingRequestDto);
        long bookingId = createdBooking.getFlight().getId();

        FlightEntity newFlight = new FlightEntity(1L, "New York", "Paris",
                "2024-12-10 22:22", 100, 100);
        BookingRequestDto updatedBookingDto = new BookingRequestDto(newFlight, passengers, true);
        updatedBookingDto.setId(bookingId);

        BookingResponseDto updatedBooking = bookingService.updateBooking(updatedBookingDto);

        assertNotNull(updatedBooking);
        assertEquals(1L, updatedBooking.getFlight().getId(), "Flight ID should not change.");
        assertEquals("Paris", updatedBooking.getFlight().getDestinationPoint(),
                "Destination point should be updated to Paris.");
    }

    @Test
    void testFindMyFlightsByNameAndSurname() {

        FlightEntity flight1 = new FlightEntity(1L, "New York", "London",
                "2024-12-01 22:22", 100, 100);
        FlightEntity flight2 = new FlightEntity(1L, "New York", "Paris",
                "2024-12-10 22:22", 100, 100);

        List<PassengerEntity> passengers1 = List.of(new PassengerEntity("John", "Doe"));
        BookingRequestDto bookingRequestDto1 = new BookingRequestDto(flight1, passengers1, true);
        bookingService.createBooking(bookingRequestDto1);

        List<PassengerEntity> passengers2 = List.of(new PassengerEntity("Jane", "Doe"));
        BookingRequestDto bookingRequestDto2 = new BookingRequestDto(flight2, passengers2, true);
        bookingService.createBooking(bookingRequestDto2);

        List<String> flights = bookingService.findMyFlightsByNameAndSurname("John", "Doe");


        assertEquals(1, flights.size(), "There should be one flight for John Doe.");
        assertTrue(flights.get(0).contains("Flight123"), "Flight123 should be listed.");
        assertFalse(flights.get(0).contains("Flight456"), "Flight456 should not be listed for John.");
    }
}

