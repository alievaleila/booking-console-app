package az.edu.turing.mapper;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightRequest;
import az.edu.turing.model.dto.response.FlightResponse;


public class FlightMapper implements EntityMapper<FlightEntity ,FlightResponse> {

    @Override
    public FlightEntity toEntity(FlightResponse flightResponse) {
        return null;
    }

    @Override
    public FlightResponse toDto(FlightEntity flightEntity) {
        return new FlightResponse(
                flightEntity.getId(),
                flightEntity.getDestinationPoint(),
                flightEntity.getDepartureTime().toLocalDate(),
                flightEntity.getDepartureTime().toLocalTime(),
                flightEntity.getAvailableSeats()
        );
    }
}
