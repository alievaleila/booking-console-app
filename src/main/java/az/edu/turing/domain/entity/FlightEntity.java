package az.edu.turing.domain.entity;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FlightEntity {

    private UUID id;
    private String departurePoint;
    private String destinationPoint;
    private int flightNumber;
    private LocalDateTime departureTime;
    private int totalSeats;
    private int availableSeats;

    public FlightEntity() {
    }

    public FlightEntity(UUID id) {
        this.id = id;
    }

    public FlightEntity(String destinationPoint, int flightNumber, int totalSeats, LocalDateTime departureTime) {
        this.id = UUID.randomUUID();
        this.departurePoint = "Kiev";
        this.destinationPoint = destinationPoint;
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.departureTime = departureTime;
        this.availableSeats = totalSeats;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FlightEntity that = (FlightEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(departurePoint, that.departurePoint) && Objects.equals(destinationPoint, that.destinationPoint) && Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePoint, destinationPoint, departureTime, totalSeats);
    }

    @Override
    public String toString() {
        return String.format(
                "FlightEntity{id='%s', departurePoint='%s', destinationPoint='%s', departureTime=%s, totalSeats=%d, availableSeats=%d}",
                id, departurePoint, destinationPoint, departureTime, totalSeats, availableSeats
        );
    }
}
