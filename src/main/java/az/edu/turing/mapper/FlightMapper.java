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
        flightEntity.setTotalSeats(flightRequestDto.getTotalSeats());
        flightEntity.setAvailableSeats(flightRequestDto.getAvailableSeats());
        return flightEntity;
    }

    @Override
    public FlightRequestDto toDto(FlightEntity flightEntity) {
        FlightRequestDto flightRequestDto = new FlightRequestDto();
        flightRequestDto.setId(flightEntity.getId());
        flightRequestDto.setDeparturePoint(flightEntity.getDeparturePoint());
        flightRequestDto.setDestinationPoint(flightEntity.getDestinationPoint());
        flightRequestDto.setTotalSeats(flightEntity.getTotalSeats());
        flightRequestDto.setAvailableSeats(flightEntity.getAvailableSeats());
        return flightRequestDto;
    }


    public FlightResponse toResp(FlightEntity flightEntity) {
        FlightResponse flightResponse = new FlightResponse();
        flightResponse.setDeparturePoint(flightEntity.getDeparturePoint());
        flightResponse.setDestinationPoint(flightEntity.getDestinationPoint());
        flightResponse.setTotalSeats(flightEntity.getTotalSeats());
        flightResponse.setAvailableSeats(flightEntity.getAvailableSeats());
        return flightResponse;
    }
}
