package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.exception.BookingNotFoundException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.BookingService;


public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final BookingMapper mapper;

    public BookingServiceImpl(BookingDao bookingDao, BookingMapper mapper) {
        this.bookingDao = bookingDao;
        this.mapper = mapper;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        BookingEntity bookingEntity = bookingDao.create(mapper.toEntity(bookingRequestDto));
        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                bookingEntity.isActive()
        );
    }

    @Override
    public BookingResponseDto deleteBooking(String id) {
        bookingDao.getById(id).stream()
                .findAny()
                .orElseThrow(() -> new BookingNotFoundException("There is no booking with this id: " + id));
        BookingEntity bookingEntity = bookingDao.deleteById(id);
        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                bookingEntity.isActive()
        );
    }

    @Override
    public BookingResponseDto getBookingDetails(String id) {
        BookingEntity bookingEntity = bookingDao.getById(id).stream()
                .findAny()
                .orElseThrow(() -> new BookingNotFoundException("There is no booking with this id:" + id));
        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                bookingEntity.isActive()
        );
    }
}
