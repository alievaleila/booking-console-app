package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.exception.FlightNotFoundException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.inter.FlightService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    private final FlightDao flightDao;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightDao flightDao, FlightMapper flightMapper) {
        this.flightDao = flightDao;
        this.flightMapper = flightMapper;
    }


    @Override
    public FlightResponse create(FlightRequestDto flightRequestDto) {
        if (flightDao.existsById(flightRequestDto.getId())) {
            throw new AlreadyExistsException("Flight already exists!");
        }
        FlightEntity flightEntity = flightDao.create(flightMapper.toEntity(flightRequestDto));
        return new FlightResponse(flightEntity.getDeparturePoint(), flightEntity.getDestinationPoint(),
                flightEntity.getDepartureTime(), flightEntity.getTotalSeats(), flightEntity.getAvailableSeats());


    }

    @Override
    public FlightResponse update(FlightRequestDto flight) {
        FlightEntity updatedEntity = flightDao.update(flightMapper.toEntity(flight));
        return new FlightResponse(updatedEntity.getDeparturePoint(), updatedEntity.getDestinationPoint(),
                updatedEntity.getDepartureTime(), updatedEntity.getTotalSeats(), updatedEntity.getAvailableSeats());

    }

    @Override
    public Collection<FlightResponse> getAllFlightResponse() {
        return flightDao.getAll().stream()
                .map(flightEntity -> flightMapper.toResp(flightMapper.toDto(flightEntity)))
                .toList();
    }

    @Override
    public FlightResponse getById(long id) {
        Optional<FlightEntity> flightEntityOptional = flightDao.getById(id);

        if (flightEntityOptional.isPresent()) {
            return flightMapper.toResp(flightMapper.toDto(flightEntityOptional.get()));
        } else {
            throw new FlightNotFoundException("Flight not found!");
        }
    }

    @Override
    public FlightResponse deleteById(long id) {
        FlightEntity flightEntity = flightDao.deleteById(id);
        if (flightEntity != null) {
            return flightMapper.toResp(flightMapper.toDto(flightEntity));
        }
        throw new FlightNotFoundException("Flight not found with ID: " + id);
    }

    @Override
    public List<FlightResponse> searchFlights(String destination, LocalDate date, int requiredSeats) {

        List<FlightEntity> flightEntityList = new ArrayList<>(flightDao.getAll());

        List<FlightResponse> matchingFlights = new ArrayList<>();

        for (FlightEntity flight : flightEntityList) {
            if (flight.getDestinationPoint().equals(destination) &&
                    flight.getDepartureTime().toLocalDate().equals(date) &&
                    flight.getAvailableSeats() >= requiredSeats) {

                matchingFlights.add(flightMapper.toResp(flightMapper.toDto(flight)));
            }
        }

        return matchingFlights;
    }

    @Override
    public boolean bookSeats(long flightId, int seats) {
        return false;
    }
}

