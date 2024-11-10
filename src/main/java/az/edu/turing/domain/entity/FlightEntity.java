package az.edu.turing.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FlightEntity {

    private String id;
    private String departurePoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private int totalSeats;
    private int availableSeats;

    public FlightEntity(String destinationPoint, LocalDateTime departureTime, int totalSeats) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.departurePoint = "Kiev";
        this.destinationPoint = destinationPoint;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FlightEntity that = (FlightEntity) object;
        return totalSeats == that.totalSeats && Objects.equals(id, that.id) && Objects.equals(departurePoint, that.departurePoint) && Objects.equals(destinationPoint, that.destinationPoint) && Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePoint, destinationPoint, departureTime, totalSeats);
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "id='" + id + '\'' +
                ", departurePoint='" + departurePoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", departureTime=" + departureTime +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
