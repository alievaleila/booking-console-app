package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.util.InputUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerInFileTest {
    @Mock
    FileUtil<PassengerEntity> fileUtilMock;
    @Mock
    InputUtil inputUtilMock;
    @Mock
    PassengerInFile passengerInFile;

    @BeforeEach
    void setUp() {
        fileUtilMock = Mockito.mock(FileUtil.class);
        inputUtilMock = Mockito.mock(InputUtil.class);
        passengerInFile = new PassengerInFile(fileUtilMock, inputUtilMock);
    }

    @Test
    void create() {
        PassengerEntity passenger = new PassengerEntity("Agadadas", "Agayev");
        List<PassengerEntity> mockData = new ArrayList<>();
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        PassengerEntity result = passengerInFile.create(passenger);

        assertEquals(result, passenger);
        assertEquals(1, mockData.size());
        verify(fileUtilMock).writeObjectToFile(mockData);

    }

    @Test
    void getAll() {
        List<PassengerEntity> mockData = new ArrayList<>(List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        ));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        List<PassengerEntity> result = new ArrayList<>(passengerInFile.getAll());

        assertEquals(mockData, result);
        assertEquals(mockData.size(), result.size());
        verify(fileUtilMock).readObjectFromFile();

    }

    @Test
    void getById() {
        List<PassengerEntity> mockData = new ArrayList<>(List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        ));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        Optional<PassengerEntity> result = passengerInFile.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(fileUtilMock).readObjectFromFile();
    }

    @Test
    void deleteById() {
        List<PassengerEntity> mockData = new ArrayList<>(List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        ));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        PassengerEntity deletedPassenger = passengerInFile.deleteById(1L);

        assertNotNull(deletedPassenger);
        assertEquals(1L, deletedPassenger.getId());
        assertEquals(1, mockData.size());
        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void update() {
        PassengerEntity originalPassenger = new PassengerEntity("Namik", "Qafarli");
        List<PassengerEntity> mockData = new ArrayList<>(List.of(originalPassenger));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        when(inputUtilMock.getString("New Passenger Name")).thenReturn("Manaf");
        when(inputUtilMock.getString("New Passenger Surname")).thenReturn("Agayev");

        PassengerEntity result = passengerInFile.update(originalPassenger);

        assertNotNull(result);
        assertEquals(result.getId(), originalPassenger.getId());
        assertEquals("Manaf", result.getName());
        assertEquals("Agayev", result.getSurname());
        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void existsById() {
        List<PassengerEntity> mockData = new ArrayList<>(List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        ));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        assertTrue(passengerInFile.existsById(1L));
        assertFalse(passengerInFile.existsById(3L));
        verify(fileUtilMock, times(2)).readObjectFromFile();
    }

    @Test
    void getPassengersByBookingId() {

    }
}