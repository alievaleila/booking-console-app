package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.exception.FlightNotFoundException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.inter.FlightService;

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
        return flightDao.getAll().stream().map(flightMapper::toResp).toList();

    }

    @Override
    public FlightResponse getById(long id) {
        return flightDao.getById(id)
                .map(flightMapper::toResp)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
    }

    @Override
    public FlightResponse deleteById(long id) {
        FlightEntity flightEntity = flightDao.deleteById(id);
        if (flightEntity != null) {
            return flightMapper.toResp(flightEntity);
        }
        throw new FlightNotFoundException("Flight not found with ID: " + id);
    }

    @Override
    public FlightResponse searchFlight(FlightRequestDto flightRequestDto) {
        List<FlightEntity> flightEntityList = (List<FlightEntity>) flightDao.getAll();
        return (FlightResponse) flightEntityList.stream().
                filter(flight -> flight.getDeparturePoint().equals(flightRequestDto.getDeparturePoint())).
                filter(flight -> flight.getDestinationPoint().equals(flightRequestDto.getDestinationPoint())).
                filter(flight -> flight.getDepartureTime().equals(flightRequestDto.getDepartureTime())).
                filter(flight -> flight.getTotalSeats().equals(flightRequestDto.getTotalSeats())).
                filter(flight -> flight.getAvailableSeats().equals(flightRequestDto.getAvailableSeats()))
                .toList().stream().map(flightMapper::toResp);
    }

    @Override
    public boolean bookSeats(long flightId, int seats) {
        Optional<FlightEntity> flightEntityOptional = flightDao.getById(flightId);
        if (flightEntityOptional.isEmpty()) {
            throw new RuntimeException("Flight not found with ID: " + flightId);
        }
        FlightEntity flightEntity = flightEntityOptional.get();
        if (flightEntity.getAvailableSeats() < seats) {
            return false;
        }
        flightEntity.setAvailableSeats(flightEntity.getAvailableSeats() - seats);
        flightDao.update(flightEntity);
        return true;

    }
}
