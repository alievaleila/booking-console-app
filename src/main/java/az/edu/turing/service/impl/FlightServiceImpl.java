package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.exception.FlightNotFoundException;
import az.edu.turing.service.inter.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FlightServiceImpl implements FlightService {

    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public FlightEntity create(FlightEntity flight) {
        return flightDao.create(flight);
    }

    @Override
    public FlightEntity update(FlightEntity flight) {
        return flightDao.update(flight);
    }

    @Override
    public List<FlightEntity> getAll() {
        return flightDao.getAll().stream().toList();
    }

    @Override
    public FlightEntity getById(UUID id) {
        return flightDao.getById(id).orElseThrow(FlightNotFoundException::new);
    }

    @Override
    public FlightEntity getByFlightNumber(int flightNumber) {
        return flightDao.getByFlightNumber(flightNumber).orElseThrow(FlightNotFoundException::new);
    }

    @Override
    public FlightEntity deleteById(UUID id) {
        return flightDao.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return flightDao.existsById(id);
    }

    @Override
    public boolean bookSeats(UUID flightId, int seats) {
        return flightDao.bookSeats(flightId, seats);
    }

    @Override
    public List<FlightEntity> getAllInNext24Hours() {
        LocalDateTime now = LocalDateTime.now();
        return getAll()
                .stream()
                .filter(e -> (e.getDepartureTime().isAfter(now) && e.getDepartureTime().isBefore(now.plusHours(24))))
                .toList();
    }
}
