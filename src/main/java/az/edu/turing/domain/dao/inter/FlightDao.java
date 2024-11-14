package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Optional;
import java.util.UUID;

public abstract class FlightDao implements Dao<FlightEntity, UUID> {

    public abstract boolean  existsById(UUID id);

    public abstract Optional<FlightEntity> getByFlightNumber(int flightNumber);

    public boolean bookSeats(UUID flightId, int seats){
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
