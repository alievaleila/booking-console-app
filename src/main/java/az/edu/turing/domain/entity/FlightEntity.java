package az.edu.turing.domain.entity;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FlightEntity {

    private Long id;
    private String departurePoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private int totalSeats;
    private int availableSeats;

    private static long idCounter = 1;

    public FlightEntity() {
    }

    public FlightEntity(String departurePoint, String destinationPoint, LocalDateTime departureTime,
                        int totalSeats, int availableSeats) {
        this.id = idCounter++;
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
