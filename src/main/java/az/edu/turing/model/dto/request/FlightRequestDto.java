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

    public FlightRequestDto(String destinationPoint, int flightNumber, String departureTime, int totalSeats) {
        this.destinationPoint = destinationPoint;
        this.flightNumber = flightNumber;
        this.departureTime = LocalDateTime.now();
        this.totalSeats = totalSeats;
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
}
