package az.edu.turing.service;

import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
    BookingResponseDto deleteBooking(String id);
    BookingResponseDto getBookingDetails(String id);
}
