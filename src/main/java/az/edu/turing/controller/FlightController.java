package az.edu.turing.controller;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.service.inter.FlightService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    FlightEntity create(FlightEntity flightEntity) {
        return flightService.create(flightEntity);
    }

    FlightEntity update(FlightEntity flightEntity) {
        return flightService.update(flightEntity);
    }

    Collection<FlightEntity> getAll() {
        return flightService.getAll();
    }

    FlightEntity getById(String id) {
        return flightService.getById(UUID.fromString(id));
    }

    FlightEntity getByFlightNumber(int flightNumber) {
        return flightService.getByFlightNumber(flightNumber);
    }

    FlightEntity deleteById(String id) {
        return flightService.deleteById(UUID.fromString(id));
    }

    boolean existsById(String id) {
        return flightService.existsById(UUID.fromString(id));
    }
}
