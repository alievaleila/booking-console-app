package az.edu.turing.controller;

import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.inter.FlightService;

import java.util.Collection;

public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
    }

    public FlightResponse create(FlightRequestDto flightRequestDto) {
        return flightService.create(flightRequestDto);
    }

    public FlightResponse update(FlightRequestDto flightRequestDto) {
        return flightService.update(flightRequestDto);
    }

    public Collection<FlightResponse> getAll() {
        return flightService.getAllFlightResponse();
    }

    public FlightResponse getById(long id) {
        return flightService.getById(id);
    }

    public FlightResponse delete(long id) {
        return flightService.deleteById(id);
    }

    public FlightResponse search(FlightRequestDto flightRequestDto) {
        return flightService.searchFlight(flightRequestDto);
    }

    public boolean bookSeats(long id, int seats) {
        return flightService.bookSeats(id, seats);
    }
}
