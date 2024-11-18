package az.edu.turing.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BookingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private FlightEntity flight;
    private List<PassengerEntity> passengers;
    private boolean isActive;

    private static long idCounter = 1;

    public BookingEntity() {
    }

    public BookingEntity(FlightEntity flight, List<PassengerEntity> passengers) {
        this.id = idCounter++;
        this.flight = flight;
        this.passengers = passengers;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public List<PassengerEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerEntity> passengers) {
        this.passengers = passengers;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        BookingEntity.idCounter = idCounter;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookingEntity that = (BookingEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(flight, that.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", flight=" + flight +
                ", passengers=" + passengers +
                ", isActive=" + isActive +
                '}';
    }
}
