package az.edu.turing.model.dto.response;

import java.time.LocalDateTime;

public class FlightResponseDto {

    private String destinationPoint;
    private int flightNumber;
    private LocalDateTime departureTime;

    public FlightResponseDto(String destinationPoint, int flightNumber, LocalDateTime departureTime) {
        this.destinationPoint = destinationPoint;
        this.flightNumber = flightNumber;
        this.departureTime = LocalDateTime.now();
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
}
