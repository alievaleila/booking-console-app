package az.edu.turing.menu;

import az.edu.turing.controller.FlightController;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.exception.MenuException;
import az.edu.turing.util.InputUtil;

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
                    break;
                case 2:
                    break;
                case 3:
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

    public void flightInfo(FlightEntity flight) {
        System.out.println(flight.getId() + " - " + flight.getDeparturePoint() + " - " + flight.getDestinationPoint() + " - " +
                flight.getFlightNumber() + " - " + flight.getDepartureTime() + " - " + flight.getTotalSeats() + " - " + flight.getAvailableSeats());
    }
}
