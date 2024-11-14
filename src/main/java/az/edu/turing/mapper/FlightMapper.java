package az.edu.turing.mapper;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightRequestDto;


public class FlightMapper implements EntityMapper<FlightEntity, FlightRequestDto> {

    @Override
    public FlightEntity toEntity(FlightRequestDto flightRequestDto) {
        return new FlightEntity(
                flightRequestDto.getId(),
                flightRequestDto.getDeparturePoint(),
                flightRequestDto.getDestinationPoint(),
                flightRequestDto.getFlightNumber(),
                flightRequestDto.getDepartureTime(),
                flightRequestDto.getTotalSeats(),
                flightRequestDto.getAvailableSeats()
        );
    }

    @Override
    public FlightRequestDto toDto(FlightEntity flightEntity) {
        return new FlightRequestDto(
                flightEntity.getDepartureTime(),
                flightEntity.getFlightNumber(),
                flightEntity.getDestinationPoint()
        );
    }
}
