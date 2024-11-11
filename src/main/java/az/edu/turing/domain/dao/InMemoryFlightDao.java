package az.edu.turing.domain.dao;

import az.edu.turing.domain.entity.FlightEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryFlightDao {
    private List<FlightEntity> flights = new ArrayList<>();


    public void addFlight(FlightEntity flight) {
        flights.add(flight);
    }

    public List<FlightEntity> getAllFlights() {
        return new ArrayList<>(flights);
    }

    public Optional<FlightEntity> findFlightById(String id) {
        return flights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();
    }

    public void deleteFlight(String id) {
        flights.removeIf(flight -> flight.getId().equals(id));
    }
}
