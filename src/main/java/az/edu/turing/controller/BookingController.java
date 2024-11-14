package az.edu.turing.controller;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.service.BookingService;
import az.edu.turing.service.FlightService;

public class BookingController {
    private final BookingService bookingService;
    private final FlightService flightService;

    public BookingController(BookingDao bookingDao, FlightDao flightDao, BookingService bookingService, FlightService flightService) {
        this.bookingService = bookingService;
        this.flightService = flightService;
    }
}
