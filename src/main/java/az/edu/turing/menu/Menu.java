package az.edu.turing.menu;

import az.edu.turing.controller.BookingController;
import az.edu.turing.controller.FlightController;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.MenuException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.util.InputUtil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public InputUtil inputUtil = new InputUtil();
    private final FlightController flightController;
    private final BookingController bookingController;
    private final FlightMapper flightMapper;
    private final PassengerMapper passengerMapper;

    public Menu(FlightController flightController, BookingController bookingController,
                FlightMapper flightMapper, PassengerMapper passengerMapper) {
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.flightMapper = flightMapper;
        this.passengerMapper = passengerMapper;
    }

    public void startMenu() {
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("Display menu started!");
        boolean running = true;
        while (running) {
            int menu = inputUtil.getInteger(
                    """
                            1.Online Board
                            2.Show The Flight Info
                            3.Search and book a flight
                            4.Cancel the booking
                            5.My flights
                            6.Exit
                            """);
            switch (menu) {
                case 1:

                    System.out.println("ID - DEPARTUREPOİNT - DESTİNATİONPOİNT - FLIGHTNUMBER - DEPARTURETIME - TOTALSEATS - AVAILABLESEATS");
                    List<FlightResponse> flightResponses = (List<FlightResponse>) flightController.getAll();
                    if (flightResponses.isEmpty()) {
                        System.out.println("There is not flight!");
                        continue;
                    }

                    boolean hasFlight = false;
                    for (FlightResponse flight : flightResponses) {
                        LocalDateTime now = LocalDateTime.now();
                        LocalDateTime flightTime = flight.getDepartureTime();
                        Duration duration = Duration.between(now, flightTime);
                        if (Math.abs(duration.toHours()) <= 24) {
                            hasFlight = true;
                            flightInfo(flight);
                        }
                    }

                    if (!hasFlight) {
                        System.out.println("There is not flight in last 24 hours!");
                    }
                    break;
                case 2:

                    System.out.println("ID - DEPARTURPOİNT - DESTİNATİONPOİNT - FLIGHTNUMBER - DEPARTURTIME - TOTALSEATS - AVAILABLESEATS");
                    List<FlightResponse> flightResponse = (List<FlightResponse>) flightController.getAll();
                    if (flightResponse.isEmpty()) {
                        System.out.println("There is not flight!");
                        continue;
                    }
                    for (FlightResponse flight : flightResponse) {
                        flightInfo(flight);
                    }
                    break;
                case 3:
                    try {

                        System.out.println("Enter the flight info!");

                        String departure = inputUtil.getString("Enter departurePoint");
                        String destination = inputUtil.getString("Enter the destination: ");
                        LocalDate departureTime = LocalDate.parse(inputUtil.getString("Enter the departure date: "));
                        int totalSeats = inputUtil.getInteger("Enter the total number of seats: ");
                        int requiredSeats = inputUtil.getInteger("Enter the required seats: ");

                        FlightRequestDto flightRequestDto = new FlightRequestDto(departure,
                                destination, departureTime.atStartOfDay(), totalSeats, requiredSeats);

                        List<FlightResponse> searched = flightController.search(destination, departureTime, requiredSeats);
                        if (searched == null) {
                            System.out.println("No flight found!");
                            break;
                        }

                        int countOfPeople = inputUtil.getInteger("How many people are you?");

                        List<PassengerEntity> passengerEntities = new ArrayList<>();
                        for (int i = 0; i < countOfPeople; i++) {
                            PassengerRequestDto passengerRequestDto = new PassengerRequestDto(
                                    inputUtil.getString("Enter passenger's name"),
                                    inputUtil.getString("Enter passenger's surname")
                            );
                            passengerEntities.add(passengerMapper.toEntity(passengerRequestDto));
                        }

                        BookingRequestDto bookingRequestDto = new BookingRequestDto(
                                flightMapper.toEntity(flightRequestDto),
                                passengerEntities,
                                true
                        );
                        bookingController.create(bookingRequestDto);

                        System.out.println("Booking successfully created!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the booking.");
                        e.printStackTrace();
                    }
                    break;
                case 4:

                    long bookingId = inputUtil.getInteger("Enter the booking ID to cancel it:");

                    bookingController.delete(bookingId);

                    break;
                case 5:

                    String name, surname;
                    name = inputUtil.getString("Enter your name");
                    surname = inputUtil.getString("Enter your surname");

                    List<String> flights = bookingController.findMyFlightsByNameAndSurname(name, surname);
                    flights.forEach(System.out::println);

                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    throw new MenuException("Menu not found!");
            }
        }
    }

    public void flightInfo(FlightResponse flightResponse) {
        System.out.println(flightResponse.getDeparturePoint() + " - " + flightResponse.getDestinationPoint() + " - " + flightResponse.getDepartureTime() + " - " +
                flightResponse.getTotalSeats() + " - " + flightResponse.getAvailableSeats());
    }
}
