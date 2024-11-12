package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FlightDaoInmemory extends FlightDao {
    private final Map<String, FlightEntity> flights = new HashMap<>();

    @Override
    public FlightEntity create(FlightEntity flightEntity) {
        flights.put(flightEntity.getId(), flightEntity);
        return flightEntity;
    }

    @Override
    public FlightEntity save(FlightEntity flight) {
        flights.put(flight.getId(), flight);
        return flight;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        return flights.values();
    }

    @Override
    public Optional<FlightEntity> getById(String id) {
        return Optional.ofNullable(flights.get(id));
    }

    @Override
    public FlightEntity deleteById(String id) {
        return flights.remove(id);
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        flights.put(flightEntity.getId(), flightEntity);
        return flightEntity;
    }
}

