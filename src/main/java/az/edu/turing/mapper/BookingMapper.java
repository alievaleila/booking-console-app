package az.edu.turing.mapper;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.model.dto.request.BookingRequestDto;

public class BookingMapper implements EntityMapper<BookingEntity, BookingRequestDto> {
    @Override
    public BookingEntity toEntity(BookingRequestDto bookingRequestDto) {
        return new BookingEntity(
                bookingRequestDto.getFlight(),
                bookingRequestDto.getPassengers(),
                bookingRequestDto.isActive()
        );
    }

    @Override
    public BookingRequestDto toDto(BookingEntity bookingEntity) {
        return new BookingRequestDto(
            bookingEntity.getFlight(),
            bookingEntity.getPassengers(),
            bookingEntity.isActive()
        );
    }
}
