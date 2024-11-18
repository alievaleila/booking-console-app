package az.edu.turing;

import az.edu.turing.controller.BookingController;
import az.edu.turing.controller.FlightController;
import az.edu.turing.domain.dao.impl.memory.BookingDaoInMemory;
import az.edu.turing.domain.dao.impl.memory.FlightDaoInMemory;
import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.menu.Menu;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.service.impl.BookingServiceImpl;
import az.edu.turing.service.impl.FlightServiceImpl;
import az.edu.turing.service.inter.BookingService;
import az.edu.turing.service.inter.FlightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class BookingManagementApp {
    public static void main(String[] args) {

        final BookingDao bookingDao =
                new BookingDaoInMemory();
//                new BookingInFile();
//                new BookingDaoPostgres();

        final BookingMapper bookingMapper = new BookingMapper();
        final BookingService bookingService =
                new BookingServiceImpl(bookingDao, bookingMapper);

        final BookingController bookingController =
                new BookingController(bookingService);

        final FlightDao flightDao =
                new FlightDaoInMemory();
//                new FlightInFile();
//                new FlightDaoPostgres();

        final FlightMapper flightMapper = new FlightMapper();
        final FlightService flightService =
                new FlightServiceImpl(flightDao, flightMapper);

        final FlightController flightController =
                new FlightController(flightService, flightMapper);

        final PassengerMapper passengerMapper = new PassengerMapper();

        run(bookingController);

        Menu menu = new Menu(flightController, bookingController, flightMapper, passengerMapper);
        menu.startMenu();
    }

    private static void run(BookingController bookingController) {
        LocalDateTime flightTime = LocalDateTime.parse("2024-02-02T20:00");
        FlightRequestDto flightRequestDto = new FlightRequestDto("Kiev",
                "Baku",
                flightTime,
                2,
                55);
        FlightMapper flightMapper = new FlightMapper();

        PassengerRequestDto passengerRequestDto = new PassengerRequestDto("Hasan", "Badalli");
        PassengerMapper passengerMapper = new PassengerMapper();
        passengerMapper.toEntity(passengerRequestDto);
        List<PassengerEntity> passengerEntities = new ArrayList<>();
        passengerEntities.add(passengerMapper.toEntity(passengerRequestDto));

        System.out.println(bookingController.create(new BookingRequestDto(flightMapper.toEntity(flightRequestDto),
                passengerEntities,
                true)));

        System.out.println("Booking with 1 ID: ");
        BookingResponseDto bookingResponseDto = bookingController.getById(1);
        System.out.println(bookingResponseDto);
    }
}

