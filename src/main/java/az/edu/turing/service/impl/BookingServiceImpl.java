package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.exception.BookingNotFoundException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.inter.BookingService;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final BookingMapper mapper;

    public BookingServiceImpl(BookingDao bookingDao, BookingMapper mapper) {
        this.bookingDao = bookingDao;
        this.mapper = mapper;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        BookingEntity bookingEntity = new BookingEntity(bookingRequestDto.getFlight(), bookingRequestDto.getPassengers());
        bookingDao.create(bookingEntity);
        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                bookingEntity.isActive()
        );
    }


    @Override
    public BookingResponseDto deleteBooking(long id) {
        BookingEntity bookingEntity = bookingDao.getById(id).stream()
                .findAny()
                .orElseThrow(() -> new BookingNotFoundException("There is no booking with this id: " + id));

        bookingDao.deleteById(id);
        System.out.println("Booking succesfully deleted.");

        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                false
        );
    }

    @Override
    public BookingResponseDto getBookingDetails(long id) {
        BookingEntity bookingEntity = bookingDao.getById(id).stream()
                .findAny()
                .orElseThrow(() -> new BookingNotFoundException("There is no booking with this id:" + id));
        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                bookingEntity.isActive()
        );
    }

    @Override
    public BookingResponseDto updateBooking(BookingRequestDto bookingRequestDto) {
        if (!bookingDao.existsById(bookingRequestDto.getId())) {
            throw new BookingNotFoundException("There is no booking with this id: " + bookingRequestDto.getId());
        }
        BookingEntity bookingEntity = bookingDao.update(mapper.toEntity(bookingRequestDto));
        return new BookingResponseDto(
                bookingEntity.getFlight(),
                bookingEntity.getPassengers(),
                bookingEntity.isActive()
        );
    }

    @Override
    public List<String> findMyFlightsByNameAndSurname(String name, String surname) {
        return bookingDao.findMyFlightsByNameAndSurname(name, surname);
    }
}
