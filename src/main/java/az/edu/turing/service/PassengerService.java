package az.edu.turing.service;

import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.PassengerResponseDto;

public interface PassengerService {
    PassengerResponseDto createPassenger(PassengerRequestDto passengerRequestDto);
}
