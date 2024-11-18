package az.edu.turing.mapper;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightRequestDto;
import az.edu.turing.model.dto.response.FlightResponse;

public class FlightMapper implements EntityMapper<FlightEntity, FlightRequestDto> {

    @Override
    public FlightEntity toEntity(FlightRequestDto flightRequestDto) {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(flightRequestDto.getId());
        flightEntity.setDeparturePoint(flightRequestDto.getDeparturePoint());
        flightEntity.setDestinationPoint(flightRequestDto.getDestinationPoint());
        flightEntity.setDepartureTime(flightRequestDto.getDepartureTime());
        flightEntity.setTotalSeats(flightRequestDto.getTotalSeats());
        flightEntity.setAvailableSeats(flightRequestDto.getAvailableSeats());
        return flightEntity;
    }

    @Override
    public FlightRequestDto toDto(FlightEntity flightEntity) {
        FlightRequestDto flightRequestDto = new FlightRequestDto();
        flightRequestDto.setDeparturePoint(flightEntity.getDeparturePoint());
        flightRequestDto.setDestinationPoint(flightEntity.getDestinationPoint());
        flightRequestDto.setDepartureTime(flightEntity.getDepartureTime());
        flightRequestDto.setTotalSeats(flightEntity.getTotalSeats());
        flightRequestDto.setAvailableSeats(flightEntity.getAvailableSeats());
        return flightRequestDto;
    }

    public FlightResponse toResp(FlightRequestDto flightRequestDto) {
        FlightResponse flightResponse = new FlightResponse();
        flightResponse.setDeparturePoint(flightRequestDto.getDeparturePoint());
        flightResponse.setDestinationPoint(flightRequestDto.getDestinationPoint());
        flightResponse.setDepartureTime(flightRequestDto.getDepartureTime());
        flightResponse.setTotalSeats(flightRequestDto.getTotalSeats());
        flightResponse.setAvailableSeats(flightRequestDto.getAvailableSeats());
        return flightResponse;
    }
}
