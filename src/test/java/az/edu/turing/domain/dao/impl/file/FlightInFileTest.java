package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.util.InputUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

class FlightInFileTest {

    @Mock
    FileUtil<FlightEntity> fileUtilMock;
    @Mock
    InputUtil inputUtilMock;
    @Mock
    FlightInFile flightInFile;

    @BeforeEach
    void setUp() {
        fileUtilMock = Mockito.mock(FileUtil.class);
        inputUtilMock = Mockito.mock(InputUtil.class);
        flightInFile = new FlightInFile(fileUtilMock, inputUtilMock);
    }

    @Test
    void create() {
        FlightEntity flightEntity = new FlightEntity("Kiev", "Istanbul", null, 30, 20);
        List<FlightEntity> mockData = new ArrayList<>();
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        FlightEntity result = flightInFile.create(flightEntity);

        assertEquals(flightEntity, result);
        assertEquals(1, mockData.size());
        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void getAll() {
        List<FlightEntity> mockData = List.of(
                new FlightEntity("Kiev", "Istanbul", null, 30, 20),
                new FlightEntity("Paris", "London", null, 50, 40)
        );
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        List<FlightEntity> result = new ArrayList<>(flightInFile.getAll());

        assertEquals(mockData, result);
        verify(fileUtilMock).readObjectFromFile();
    }

    @Test
    void getById() {
        List<FlightEntity> mockData = new ArrayList<>(
                List.of(
                        new FlightEntity("Kiev", "Istanbul", null, 30, 20),
                        new FlightEntity("Paris", "London", null, 50, 40)
                )
        );
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);
        Optional<FlightEntity> result = flightInFile.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(fileUtilMock).readObjectFromFile();
    }

    @Test
    void deleteById() {
        List<FlightEntity> mockData = new ArrayList<>(List.of(
                new FlightEntity("Kiev", "Istanbul", null, 30, 20),
                new FlightEntity("Paris", "London", null, 50, 40)
        ));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        FlightEntity deletedFlight = flightInFile.deleteById(1L);
        assertNotNull(deletedFlight);
        assertEquals(1L, deletedFlight.getId());
        assertEquals(1, mockData.size());

        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void update() {
        FlightEntity originalFlight = new FlightEntity("Kiev", "Istanbul", null, 30, 20);
        List<FlightEntity> mockData = List.of(originalFlight);
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        when(inputUtilMock.getString("Enter the new Departure Point")).thenReturn("Baku");
        when(inputUtilMock.getString("Enter the new Destination Point")).thenReturn("Berlin");
        when(inputUtilMock.getInteger("Enter the available seats")).thenReturn(25);
        when(inputUtilMock.getInteger("Enter the total seats")).thenReturn(35);
        when(inputUtilMock.getLocalDateTime("Enter the new departure time")).thenReturn(null);

        FlightEntity updatedFlight = flightInFile.update(originalFlight);

        assertNotNull(updatedFlight);
        assertEquals("Baku", updatedFlight.getDeparturePoint());
        assertEquals("Berlin", updatedFlight.getDestinationPoint());
        assertEquals(25, updatedFlight.getAvailableSeats());
        assertEquals(35, updatedFlight.getTotalSeats());
        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void existsById() {
        List<FlightEntity> mockData = new ArrayList<>(List.of(
                new FlightEntity("Kiev", "Istanbul", null, 30, 20),
                new FlightEntity("Paris", "London", null, 50, 40)
        ));
         when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

         assertTrue(flightInFile.existsById(1L));
         assertFalse(flightInFile.existsById(3L));
        verify(fileUtilMock, times(2)).readObjectFromFile();
    }
}