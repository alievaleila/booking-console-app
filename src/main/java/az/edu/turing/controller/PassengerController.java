package az.edu.turing.controller;

import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.PassengerResponseDto;
import az.edu.turing.service.inter.PassengerService;

public class PassengerController {
    public final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public PassengerResponseDto create(PassengerRequestDto passengerRequestDto) {
        return passengerService.createPassenger(passengerRequestDto);
    }
}
