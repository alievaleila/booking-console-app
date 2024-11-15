package az.edu.turing.menu;

import az.edu.turing.controller.FlightController;
import az.edu.turing.exception.MenuException;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.util.InputUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Menu {
    public InputUtil inputUtil = new InputUtil();
    private final FlightController flightController;

    public Menu(FlightController flightController) {
        this.flightController = flightController;
        displayMenu();
    }

    public void displayMenu() {
        while (true) {
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

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("We are waiting again!");
                    System.exit(0);
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
