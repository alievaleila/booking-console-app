package az.edu.turing.model.dto.request;

import java.time.LocalDateTime;

public class FlightRequestDto {

    private long id;
    private String departurePoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private int totalSeats;
    private int availableSeats;

    private static long idCounter = 1;

    public FlightRequestDto() {
    }

    public FlightRequestDto(String departurePoint, String destinationPoint, LocalDateTime departureTime, int totalSeats, int availableSeats) {
        this.id = idCounter++;
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "FlightRequest{" +
                "id=" + id +
                ", departurePoint='" + departurePoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", departureTime=" + departureTime +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
