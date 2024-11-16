package az.edu.turing.menu;

import az.edu.turing.controller.BookingController;
import az.edu.turing.controller.FlightController;
import az.edu.turing.controller.PassengerController;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.MenuException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.request.BookingRequestDto;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.BookingResponseDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.model.dto.response.PassengerResponseDto;
import az.edu.turing.util.InputUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Menu {
    public InputUtil inputUtil = new InputUtil();
    private final FlightController flightController;
    private final BookingController bookingController;
    private final FlightMapper flightMapper;

    public Menu(FlightController flightController, BookingController bookingController, FlightMapper flightMapper) {
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.flightMapper = flightMapper;
        displayMenu();
    }

    public void displayMenu() {
        boolean stop = false;
        while (stop) {
            System.out.println("Choose the select menu!");
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
                    System.out.println("ID - DEPARTURPOİNT - DESTİNATİONPOİNT - FLIGHTNUMBER - DEPARTURTIME - TOTALSEATS - AVAILABLESEATS");
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
                    System.out.println("Enter the flight info!");
                    int flightId = inputUtil.getInteger("Enter the flight total seats");
                    String flightStart = inputUtil.getString("Enter the flightDepartur Point");
                    String flightTo = inputUtil.getString("Enter the flight destination Point");
                    LocalDateTime flightTime = LocalDateTime.parse(inputUtil.getString("Enter the flight time"));
                    int totalSeats = inputUtil.getInteger("Enter the flight total seats");
                    int availableSeats = inputUtil.getInteger("Enter the flight availableseats");
                    FlightRequestDto flightResponse1 = new FlightRequestDto(flightId, flightStart, flightTo, flightTime, totalSeats, availableSeats);

                    FlightResponse searchFlight = flightController.search(flightResponse1);
                    if (searchFlight != null) {
                        flightInfo(searchFlight);
                    } else {
                        System.out.println("There is not flight!");
                    }

                    BookingRequestDto bookingRequestDto = new BookingRequestDto(
                            flightMapper.toEntity(flightResponse1),
                            true
                    );

                    bookingController.create(bookingRequestDto);

                    break;
                case 4:

                    long bookingId = inputUtil.getInteger("Enter the booking ID to cancel it:");

                    bookingController.delete(bookingId);

                    break;
                case 5:

                    String name, surname;
                    while (true) {
                        name = inputUtil.getString("Enter your name").trim();
                        if (!name.isEmpty()) break;
                        System.out.println("Name cannot be empty. Please try again.");
                    }
                    while (true) {
                        surname = inputUtil.getString("Enter your surname").trim();
                        if (!surname.isEmpty()) break;
                        System.out.println("Surname cannot be empty. Please try again.");
                    }

                    List<String> flights = bookingController.findMyFlightsByNameAndSurname(name, surname);
                    flights.forEach(System.out::println);

                    break;
                case 6:
                    System.out.println("We are waiting again!");
                    stop = true;
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
