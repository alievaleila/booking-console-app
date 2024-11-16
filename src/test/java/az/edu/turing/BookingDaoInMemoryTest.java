package az.edu.turing;

import az.edu.turing.domain.dao.impl.memory.BookingDaoInMemory;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingDaoInMemoryTest {

    private BookingDaoInMemory bookingDao;

    @BeforeEach
    void setUp() {
        bookingDao = new BookingDaoInMemory();
    }

    @Test
    void testCreateBooking() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("Ali", "Hagverdiyev"));

        BookingEntity booking = new BookingEntity(flight, passengers);
        bookingDao.create(booking);

        assertTrue(bookingDao.existsById(booking.getId()));
        assertEquals(44, booking.getFlight().getAvailableSeats());
    }

    @Test
    void testGetBookingById() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("Ali", "Hagverdiyev"));

        BookingEntity booking = new BookingEntity(flight, passengers);
        bookingDao.create(booking);

        Optional<BookingEntity> foundBooking = bookingDao.getById(booking.getId());
        assertTrue(foundBooking.isPresent());
        assertEquals(booking, foundBooking.get());
    }

    @Test
    void testDeleteBooking() {
        FlightEntity flight = new FlightEntity("Baku", "Tbilisi", LocalDateTime.now(), 50, 45);
        List<PassengerEntity> passengers = List.of(new PassengerEntity("Ali", "Hagverdiyev"));

        BookingEntity booking = new BookingEntity(flight, passengers);
        bookingDao.create(booking);

        BookingEntity deletedBooking = bookingDao.deleteById(booking.getId());
        assertFalse(deletedBooking.isActive());
    }
}
