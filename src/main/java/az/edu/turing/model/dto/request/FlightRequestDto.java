package az.edu.turing.model.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public class FlightRequestDto {

    private UUID id;
    private String departurePoint;
    private String destinationPoint;
    private int flightNumber;
    private LocalDateTime departureTime;
    private int totalSeats;
    private int availableSeats;

    public FlightRequestDto(UUID id, String departurePoint, String destinationPoint, int flightNumber,
                            LocalDateTime departureTime, int totalSeats, int availableSeats) {
        this.id = id;
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public FlightRequestDto(LocalDateTime departureTime, int flightNumber, String destinationPoint) {
        this.departureTime = departureTime;
        this.flightNumber = flightNumber;
        this.destinationPoint = destinationPoint;
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
}
