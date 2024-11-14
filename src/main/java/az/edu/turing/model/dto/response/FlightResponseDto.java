package az.edu.turing.model.dto.response;

import java.time.LocalDateTime;

public class FlightResponseDto {


    private String destinationPoint;
    private int flightNumber;
    private LocalDateTime departureTime;

    public FlightResponseDto(String destinationPoint, int flightNumber, LocalDateTime departureTime) {
        this.destinationPoint = destinationPoint;
        this.flightNumber = flightNumber;
        this.departureTime = LocalDateTime.now();
    }
}
