package az.edu.turing.model.dto.response;

import java.time.LocalDateTime;

public class FlightResponse {

    private String departurePoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private int totalSeats;
    private int availableSeats;

    public FlightResponse() {
    }

    public FlightResponse(String departurePoint, String destinationPoint, LocalDateTime departureTime, int totalSeats, int availableSeats) {
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.departureTime =departureTime ;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
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
    public String toString() {
        return "FlightResponse{" +
                "departurePoint='" + departurePoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", departureTime=" + departureTime +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
