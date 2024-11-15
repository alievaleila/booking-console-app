package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Optional;

public abstract class FlightDao implements Dao<FlightEntity, Long> {

    public abstract boolean existsById(Long id);

    public boolean bookSeats(Long flightId, int seats) {
        Optional<FlightEntity> flight = getById(flightId);
        if (flight.isEmpty() || flight.get().getAvailableSeats() < seats) {
            return false;
        }
        FlightEntity flightEntity = flight.get();
        flightEntity.setAvailableSeats(flightEntity.getAvailableSeats() - seats);
        update(flightEntity);
        return true;
    }
}
