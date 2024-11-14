package az.edu.turing.model.dto.request;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.List;
import java.util.UUID;

public class BookingRequestDto {

    private FlightEntity flight;
    private List<PassengerEntity> passengers;
    private boolean isActive;

    public BookingRequestDto() {
    }

    public BookingRequestDto( FlightEntity flight, List<PassengerEntity> passengers, boolean isActive) {
        this.flight = flight;
        this.passengers = passengers;
        this.isActive = isActive;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlightId(FlightEntity flight) {
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
        return String.format("BookingRequestDto{flight=%s, passengers=%d, isActive=%b}", flight, passengers.size(), isActive);
    }

}
