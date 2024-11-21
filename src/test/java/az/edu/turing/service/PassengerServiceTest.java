package az.edu.turing.service;

import az.edu.turing.domain.dao.impl.memory.PassengerDaoInMemory;
import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.request.PassengerRequestDto;
import az.edu.turing.model.dto.response.PassengerResponseDto;
import az.edu.turing.service.impl.PassengerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerServiceTest {
    private PassengerServiceImpl passengerService;
    private PassengerDao passengerDao;
    private PassengerMapper passengerMapper;

    @BeforeEach
    public void setUp() {

        passengerDao = new PassengerDaoInMemory();
        passengerMapper = new PassengerMapper();
        passengerService = new PassengerServiceImpl(passengerDao, passengerMapper);
    }

    @Test
    public void testCreatePassenger_WhenPassengerDoesNotExist_ShouldCreatePassenger() {
        Long passengerId = 1L;
        String name = "John";
        String surname = "Doe";
        PassengerRequestDto passengerRequestDto = new PassengerRequestDto(passengerId, name, surname);

        assertFalse(passengerDao.existsById(passengerId), "Yolcu əvvəlcədən mövcud olmamalıdır");

        PassengerResponseDto response = passengerService.createPassenger(passengerRequestDto);

        assertEquals(name, response.getName());
        assertEquals(surname, response.getSurname());

        assertTrue(passengerDao.existsById(passengerId), "Yolcu yaradıldıqdan sonra mövcud olmalıdır.");
    }
}


