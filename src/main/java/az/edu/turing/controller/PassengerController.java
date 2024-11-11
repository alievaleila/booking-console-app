package az.edu.turing.controller;

import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.PassengerResponseDto;
import az.edu.turing.service.PassengerService;

public class PassengerController {
    public final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public PassengerResponseDto createPassenger(PassengerRequestDto passengerRequestDto) {
        return passengerService.createPassenger(passengerRequestDto);
    }
}
