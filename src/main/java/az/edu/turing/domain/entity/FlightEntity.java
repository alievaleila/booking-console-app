package az.edu.turing.domain.entity;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class FlightEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String departurePoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private Integer totalSeats;
    private Integer availableSeats;

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

    public FlightEntity(Long id, String departurePoint, String destinationPoint, String departureTimeStr,
                        Integer totalSeats, Integer availableSeats) {
        this.id = id;
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.departureTime = convertStringToLocalDateTime(departureTimeStr);
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    private LocalDateTime convertStringToLocalDateTime(String departureTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(departureTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'.");
        }
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

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getAvailableSeats() {
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
