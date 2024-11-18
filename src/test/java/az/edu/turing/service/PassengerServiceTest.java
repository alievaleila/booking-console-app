package az.edu.turing.service;

import az.edu.turing.domain.dao.impl.memory.PassengerDaoInMemory;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.PassengerResponseDto;
import az.edu.turing.service.impl.PassengerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerServiceTest {
    private PassengerServiceImpl passengerService;
    private PassengerDaoInMemory passengerDaoInMemory;
    private PassengerMapper passengerMapper;

    @BeforeEach
    void setUp() {
        passengerDaoInMemory = new PassengerDaoInMemory();
        passengerMapper = new PassengerMapper();
        passengerService = new PassengerServiceImpl(passengerDaoInMemory, passengerMapper);

        PassengerDaoInMemory.PASSENGERS.clear();
    }

    @Test
    void testCreatePassenger() {
        PassengerRequestDto passengerRequestDto = new PassengerRequestDto(1L, "John", "Doe");

        PassengerResponseDto responseDto = passengerService.createPassenger(passengerRequestDto);

        assertNotNull(responseDto);
        assertEquals("John", responseDto.getName(), "Passenger name should be 'John'.");
        assertEquals("Doe", responseDto.getSurname(), "Passenger surname should be 'Doe'.");
        assertTrue(passengerDaoInMemory.existsById(1L), "Passenger should exist in the database.");
    }

    @Test
    void testCreatePassengerAlreadyExists() {
        PassengerRequestDto passengerRequestDto = new PassengerRequestDto(1L, "John", "Doe");
        passengerService.createPassenger(passengerRequestDto);

        PassengerRequestDto duplicatePassengerRequestDto = new PassengerRequestDto(1L, "John", "Doe");

        assertThrows(AlreadyExistsException.class, () -> passengerService.createPassenger(duplicatePassengerRequestDto),
                "Should throw AlreadyExistsException when creating a duplicate passenger.");
    }
}



