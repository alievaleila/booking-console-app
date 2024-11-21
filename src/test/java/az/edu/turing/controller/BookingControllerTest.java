package az.edu.turing.controller;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.inter.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingControllerTest {

    private final BookingService bookingService = new BookingService() {
        @Override
        public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
            BookingResponseDto response = new BookingResponseDto();
            response.setPassengers(bookingRequestDto.getPassengers());
            return response;
        }

        @Override
        public BookingResponseDto getBookingDetails(long id) {
            BookingResponseDto response = new BookingResponseDto();
            response.setPassengers(List.of(new PassengerEntity("Hasan", "Badalli")));
            return response;
        }

        @Override
        public List<String> findMyFlightsByNameAndSurname(String name, String surname) {
            return List.of("Flight123");
        }

        @Override
        public BookingResponseDto updateBooking(BookingRequestDto bookingRequestDto) {
            BookingResponseDto response = new BookingResponseDto();
            response.setPassengers(bookingRequestDto.getPassengers());
            return response;
        }

        @Override
        public BookingResponseDto deleteBooking(long id) {
            BookingResponseDto response = new BookingResponseDto();
            response.setPassengers(List.of(new PassengerEntity("Farid", "Karimli")));
            return response;
        }
    };

    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        bookingController = new BookingController(bookingService);
    }

    @Test
    void create_ShouldReturnBookingResponseDto() {
        BookingRequestDto requestDto = new BookingRequestDto();
        PassengerEntity passengerEntity = new PassengerEntity("Farid", "Karimli");
        requestDto.setPassengers(List.of(passengerEntity));

        BookingResponseDto response = bookingController.create(requestDto);

        assertNotNull(response, "Response should not be null");
        assertTrue(
                response.getPassengers().stream()
                        .anyMatch(passenger -> passenger.getName().equals("Farid") &&
                                passenger.getSurname().equals("Karimli")),
                "Passenger 'Farid Karimli' should exist in the response"
        );
    }

    @Test
    void getById_ShouldReturnCorrectBooking() {
        BookingResponseDto response = bookingController.getById(1L);

        assertNotNull(response);
        assertTrue(
                response.getPassengers().stream()
                        .anyMatch(passenger -> passenger.getName().equals("Hasan") &&
                                passenger.getSurname().equals("Badalli")),
                "Response should contain the expected passenger"
        );
    }

    @Test
    void findMyFlightsByNameAndSurname_ShouldReturnListOfFlights() {
        List<String> flights = bookingController.findMyFlightsByNameAndSurname("John", "Doe");

        assertNotNull(flights);
        assertFalse(flights.isEmpty(), "Flights list should not be empty");
        assertEquals("Flight123", flights.get(0), "First flight should match");
    }

    @Test
    void update_ShouldUpdateBookingSuccessfully() {
        BookingRequestDto requestDto = new BookingRequestDto();
        PassengerEntity passengerEntity = new PassengerEntity("Farid", "Karimli");
        requestDto.setPassengers(List.of(passengerEntity));

        BookingResponseDto response = bookingController.update(requestDto);

        assertNotNull(response);
        assertTrue(
                response.getPassengers().stream()
                        .anyMatch(passenger -> passenger.getName().equals("Farid") &&
                                passenger.getSurname().equals("Karimli")),
                "Response should contain the expected passenger"
        );
    }

    @Test
    void delete_ShouldReturnDeletedBooking() {
        BookingResponseDto response = bookingController.delete(1L);

        assertNotNull(response);
        assertTrue(
                response.getPassengers().stream()
                        .anyMatch(passenger -> passenger.getName().equals("Farid") &&
                                passenger.getSurname().equals("Karimli")),
                "Response should contain the expected passenger"
        );
    }
}




