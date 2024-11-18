package az.edu.turing.model.dto.response;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.List;

public class BookingResponseDto {

    private FlightEntity flight;
    private List<PassengerEntity> passengers;
    private boolean isActive;

    public BookingResponseDto() {
    }

    public BookingResponseDto(FlightEntity flight, List<PassengerEntity> passengers, boolean isActive) {
        this.flight = flight;
        this.passengers = passengers;
        this.isActive = isActive;
    }


    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public List<PassengerEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerEntity> passengers) {
        this.passengers = passengers;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return String.format("BookingResponseDto{flight=%s, passengers=%s, isActive=%b}", flight, passengers, isActive);
    }
}
