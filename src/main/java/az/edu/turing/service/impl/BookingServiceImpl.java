package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.BookingNotFoundException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.inter.BookingService;

import java.util.List;
import java.util.Scanner;


public class BookingServiceImpl implements BookingService {

    Scanner in = new Scanner(System.in);
    private final BookingDao bookingDao;
    private final BookingMapper mapper;

    public BookingServiceImpl(BookingDao bookingDao, BookingMapper mapper) {
        this.bookingDao = bookingDao;
        this.mapper = mapper;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        BookingEntity bookingEntity=null ;
        try {

            System.out.println("How many people are you?");
            int countOfPeople =0;

            while (true) {
                try {
                    countOfPeople = Integer.parseInt(in.nextLine());
                    if (countOfPeople <= 0) {
                        System.out.println("Please enter a valid positive number.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            for (int i = 0; i < countOfPeople; i++) {
                String name, surname;

                while (true) {
                    System.out.println("Enter your name: ");
                    name = in.nextLine().trim();
                    if (!name.isEmpty()) break;
                    System.out.println("Name cannot be empty. Please try again.");
                }

                while (true) {
                    System.out.println("Enter your surname: ");
                    surname = in.nextLine().trim();
                    if (!surname.isEmpty()) break;
                    System.out.println("Surname cannot be empty. Please try again.");
                }

                bookingEntity = bookingDao.create(mapper.toEntity(bookingRequestDto));

                bookingEntity.getPassengers().add(new PassengerEntity(name, surname));
            }

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

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
                bookingEntity.isActive()
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
