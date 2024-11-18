package az.edu.turing.memory;

import az.edu.turing.domain.dao.impl.memory.PassengerDaoInMemory;
import az.edu.turing.domain.entity.PassengerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerDaoInMemoryTest {
    private PassengerDaoInMemory passengerDao;

    @BeforeEach
    void setUp() {
        passengerDao = new PassengerDaoInMemory();
    }

    @Test
    void testCreatePassenger() {
        PassengerEntity passenger = new PassengerEntity("Ali", "Hagverdiyev");
        passengerDao.create(passenger);

        assertTrue(passengerDao.existsById(passenger.getId()));
    }

    @Test
    void testGetPassengerById() {
        PassengerEntity passenger = new PassengerEntity("Ali", "Hagverdiyev");
        passengerDao.create(passenger);

        Optional<PassengerEntity> foundPassenger = passengerDao.getById(passenger.getId());
        assertTrue(foundPassenger.isPresent());
        assertEquals(passenger, foundPassenger.get());
    }

    @Test
    void testGetAllPassengers() {
        PassengerEntity passenger1 = new PassengerEntity("Ali", "Hagverdiyev");
        PassengerEntity passenger2 = new PassengerEntity("Amin", "Hagverdiyev");

        passengerDao.create(passenger1);
        passengerDao.create(passenger2);

        assertEquals(2, PassengerDaoInMemory.PASSENGERS.size());
    }

    @Test
    void testDeletePassengerById() {
        PassengerEntity passenger = new PassengerEntity("Ali", "Hagverdiyev");
        passengerDao.create(passenger);

        passengerDao.deleteById(passenger.getId());
        assertFalse(passengerDao.existsById(passenger.getId()));
    }
}

