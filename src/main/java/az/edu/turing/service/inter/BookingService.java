package az.edu.turing.service.inter;

import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
    BookingResponseDto deleteBooking(long id);
    BookingResponseDto getBookingDetails(long id);
    BookingResponseDto updateBooking(BookingRequestDto bookingRequestDto);
    List<String> findMyFlightsByNameAndSurname(String name, String surname);
}
