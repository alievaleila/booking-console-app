package az.edu.turing;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;

import java.time.LocalDateTime;
import java.util.UUID;


public class BookingManagementApp {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().substring(0, 8));

        //Checking Entities toString methods
        PassengerEntity passengerEntity = new PassengerEntity("Samir", "Eliyev");
        FlightEntity flightEntity = new FlightEntity("Africa", 123, 55, LocalDateTime.now());
        BookingEntity bookingEntity = new BookingEntity(flightEntity);
        System.out.println(passengerEntity);
        System.out.println(flightEntity);
        System.out.println(bookingEntity);
    }
}
