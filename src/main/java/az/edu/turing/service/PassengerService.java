package az.edu.turing.service;

import az.edu.turing.model.dto.PassengerRequestDto;
import az.edu.turing.model.dto.PassengerResponseDto;

public interface PassengerService {
    PassengerResponseDto createPassenger(PassengerRequestDto passengerRequestDto);
}
