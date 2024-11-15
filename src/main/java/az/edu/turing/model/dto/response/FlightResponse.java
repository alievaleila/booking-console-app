package az.edu.turing.model.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightResponse {

    private long id;
    private String destinationPoint;
    private LocalDate date;
    private LocalTime time;
    private int availableSeats;

    public FlightResponse(long id, String destinationPoint, LocalDate date, LocalTime time, int availableSeats) {
        this.id = id;
        this.destinationPoint = destinationPoint;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
