package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FlightDaoInMemory extends FlightDao {

    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private final Map<Long, FlightEntity> flights = new HashMap<>();

    public FlightDaoInMemory() {
        addFlight("Kiev", "Baku", 3, 5);
        addFlight("Kiev", "Moscow", 2, 3);
        addFlight("Kiev", "Istanbul", 6, 8);
    }

    private void addFlight(String departure, String destination, int depHoursFromNow, int seats) {
        FlightEntity flight =
                new FlightEntity(departure, destination, LocalDateTime.now().plusHours(depHoursFromNow), seats, seats);
        create(flight);
    }

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
    public Optional<FlightEntity> getById(Long id) {
        return Optional.ofNullable(flights.get(id));
    }

    @Override
    public FlightEntity deleteById(Long id) {
        return flights.remove(id);
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        flights.put(flightEntity.getId(), flightEntity);
        return flightEntity;
    }

    @Override
    public boolean existsById(Long flightId) {
        return flights.containsKey(flightId);
    }
}

