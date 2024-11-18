package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.anyString;

class BookingInFileTest {


    @Mock
    FileUtil<BookingEntity> fileUtilMock;
    @Mock
    InputUtil inputUtilMock;
    @Mock
    BookingInFile bookingInFile;

    @BeforeEach
    void setUp() {
        fileUtilMock = Mockito.mock(FileUtil.class);
        inputUtilMock = Mockito.mock(InputUtil.class);
        bookingInFile = new BookingInFile(fileUtilMock, inputUtilMock);
    }

    @Test
    void existsById() {
        FlightEntity flight = new FlightEntity("Kiev", "Istanbul",
                null, 30, 12);
        List<PassengerEntity> passengers = List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        );
        List<BookingEntity> mockData = List.of(
                new BookingEntity(flight, passengers),
                new BookingEntity(flight, passengers)
        );

        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        assertTrue(bookingInFile.existsById(1L));
        assertFalse(bookingInFile.existsById(3L));
        verify(fileUtilMock, times(2)).readObjectFromFile();
    }

    @Test
    void findMyFlightsByNameAndSurname() {
    }

    @Test
    void create() {
        FlightEntity flight = new FlightEntity("Kiev", "Istanbul",
                null, 30, 12);
        List<PassengerEntity> passengers = List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        );
        BookingEntity bookingEntity = new BookingEntity(flight, passengers);
        List<BookingEntity> mockData = new ArrayList<>();

        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        BookingEntity result = bookingInFile.create(bookingEntity);

        assertEquals(result, bookingEntity);
        assertEquals(1, mockData.size());
        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void getAll() {
        FlightEntity flight1 = new FlightEntity("Kiev", "Istanbul",
                null, 30, 12);
        FlightEntity flight2 = new FlightEntity("Kiev", "USA",
                null, 33, 12);
        List<BookingEntity> mockData = getBookingEntities(flight1, flight2);

        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);
        List<BookingEntity> result = new ArrayList<>(bookingInFile.getAll());

        assertEquals(mockData, result);
        verify(fileUtilMock).readObjectFromFile();
    }

    @Test
    void getById() {
        FlightEntity flight1 = new FlightEntity("Kiev", "Istanbul",
                null, 30, 12);
        FlightEntity flight2 = new FlightEntity("Kiev", "USA",
                null, 33, 12);
        List<BookingEntity> mockData = getBookingEntities(flight1, flight2);
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        Optional<BookingEntity> result = bookingInFile.getById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getId());
        verify(fileUtilMock).readObjectFromFile();
    }

    @Test
    void deleteById() {
        FlightEntity flight = new FlightEntity("Kiev", "Istanbul",
                null, 30, 12);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("Nadir", "Qafarzade"));
        BookingEntity bookingEntity = new BookingEntity(flight, passengers);
        List<BookingEntity> mockData = new ArrayList<>(List.of(bookingEntity));
        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);

        BookingEntity result = bookingInFile.deleteById(1L);

        assertEquals(bookingEntity, result);
        assertTrue(mockData.isEmpty());
        verify(fileUtilMock).writeObjectToFile(mockData);
    }

    @Test
    void update() {
        FlightEntity flight = new FlightEntity("Kiev", "Istanbul",
                null, 30, 12);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("Nadir", "Qafarzade"));
        BookingEntity bookingEntity = new BookingEntity(flight, passengers);
        List<BookingEntity> mockData = new ArrayList<>(List.of(bookingEntity));

        when(fileUtilMock.readObjectFromFile()).thenReturn(mockData);
        when(inputUtilMock.getBoolean(anyString())).thenReturn(true);
        when(inputUtilMock.getFlightEntity(anyString())).thenReturn(null);

        BookingEntity result = bookingInFile.update(bookingEntity);

        assertTrue(result.isActive());
        verify(fileUtilMock).writeObjectToFile(mockData);
        verify(inputUtilMock).getBoolean(anyString());
        verify(inputUtilMock).getFlightEntity(anyString());

    }

    private static List<BookingEntity> getBookingEntities(FlightEntity flight1, FlightEntity flight2) {
        List<PassengerEntity> passengers1 = List.of(
                new PassengerEntity("Samir", "Eliyev"),
                new PassengerEntity("Ferhad", "Sirinov")
        );
        List<PassengerEntity> passengers2 = List.of(
                new PassengerEntity("Nurlan", "Haciyev"),
                new PassengerEntity("Kamil", "Sirinov")
        );

        return List.of(
                new BookingEntity(flight1, passengers1),
                new BookingEntity(flight2, passengers2)
        );
    }
}