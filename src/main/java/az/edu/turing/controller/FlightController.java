package az.edu.turing.controller;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightRequest;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.inter.FlightService;

import java.util.Collection;
import java.util.List;

public class FlightController {

    private final FlightService flightService;

    private final FlightMapper flightMapper;

    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
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

    FlightResponse getById(long id) {
        return flightMapper.toDto(flightService.getById(id));
    }

    FlightEntity deleteById(long id) {
        return flightService.deleteById(id);
    }

    boolean existsById(long id) {
        return flightService.existsById(id);
    }

    boolean bookSeats(long flightId, int seats) {
        return flightService.bookSeats(flightId, seats);
    }

    List<FlightResponse> getAllInNext24Hours() {
        return getAll().stream().map(flightMapper::toDto).toList();
    }

    List<FlightResponse> searchFlights(FlightRequest request) {
        return flightService.searchFlights(request).stream().map(flightMapper::toDto).toList();
    }
}
