package az.edu.turing.domain.dao.impl.memory;


import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FlightDaoInMemory extends FlightDao {

    private final Map<UUID, FlightEntity> flights = new HashMap<>();

    @Override
    public FlightEntity create(FlightEntity flight) {
        flights.put(flight.getId(), flight);
        return flight;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        return flights.values();
    }

    @Override
    public Optional<FlightEntity> getById(UUID id) {
        return Optional.ofNullable(flights.get(id));
    }

    @Override
    public FlightEntity deleteById(UUID id) {
        return flights.remove(id);
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        flights.put(flightEntity.getId(), flightEntity);
        return flightEntity;
    }

    @Override
    public boolean existsById(UUID flightId) {
        return flights.containsKey(flightId);
    }

    @Override
    public Optional<FlightEntity> getByFlightNumber(int flightNumber) {
        return flights.values().stream().filter(e -> e.getFlightNumber() == flightNumber).findFirst();
    }
}

