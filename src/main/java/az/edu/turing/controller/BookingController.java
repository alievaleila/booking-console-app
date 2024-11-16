package az.edu.turing.controller;

import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.inter.BookingService;

import java.util.List;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingResponseDto create(BookingRequestDto bookingRequestDto) {
        return bookingService.createBooking(bookingRequestDto);
    }

    public BookingResponseDto getById(long id) {
        return bookingService.getBookingDetails(id);
    }

    public List<String> findMyFlightsByNameAndSurname(String name, String surname) {
        return bookingService.findMyFlightsByNameAndSurname(name, surname);
    }

    public BookingResponseDto update(BookingRequestDto bookingRequestDto) {
        return bookingService.updateBooking(bookingRequestDto);
    }

    public BookingResponseDto delete(long id) {
        return bookingService.deleteBooking(id);
    }
}
