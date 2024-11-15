package az.edu.turing.controller;

import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.BookingService;

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

    public BookingResponseDto update(BookingRequestDto bookingRequestDto) {
        return bookingService.updateBooking(bookingRequestDto);
    }

    public BookingResponseDto delete(long id) {
        return bookingService.deleteBooking(id);
    }
}
