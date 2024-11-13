package az.edu.turing.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BookingEntity {

    private UUID id;
    private FlightEntity flight;
    private List<PassengerEntity> passengers;
    private boolean isActive;

    public BookingEntity() {
    }

    public BookingEntity(FlightEntity flight, List<PassengerEntity> passengers, boolean isActive) {
        this.id = UUID.randomUUID();
        this.flight = flight;
        this.passengers = passengers;
        this.isActive = isActive;
    }

    public BookingEntity(FlightEntity flight) {
        this.id = UUID.randomUUID();
        this.flight = flight;
        this.passengers = new ArrayList<>();
        this.isActive = true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookingEntity that = (BookingEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(flight, that.flight) && Objects.equals(passengers, that.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, passengers);
    }

    @Override
    public String toString() {
        return String.format("BookingEntity{id='%s', flightNumber=%d, passengers=%d, isActive=%b}", id.toString(), flight.getFlightNumber(), passengers.size(), isActive);
    }
}
