package az.edu.turing;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.impl.database.FlightDaoPostgres;
import az.edu.turing.domain.entity.FlightEntity;

import java.time.LocalDateTime;


public class BookingManagementApp {
    public static void main(String[] args) {
        //Checking Entities toString methods
//        PassengerEntity passengerEntity = new PassengerEntity("Samir", "Eliyev");
//        FlightEntity flightEntity = new FlightEntity("Africa", 123, 55, LocalDateTime.now());
//        BookingEntity bookingEntity = new BookingEntity(flightEntity);
//        System.out.println(passengerEntity);
//        System.out.println(flightEntity);
//        System.out.println(bookingEntity);

        FlightEntity flightEntity = new FlightEntity("Europa", 1234, 55, LocalDateTime.now());
        FlightDaoPostgres flightDaoPostgres = new FlightDaoPostgres(new ConnectionHelper());
        flightDaoPostgres.create(flightEntity);
    }
}
