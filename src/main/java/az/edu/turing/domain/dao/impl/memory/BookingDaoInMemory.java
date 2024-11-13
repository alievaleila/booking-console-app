package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.exception.AlreadyExistsException;
import az.edu.turing.exception.BookingNotFoundException;

import java.util.*;

public class BookingDaoInMemory extends BookingDao {

    private static final Map<String, BookingEntity> BOOKINGS = new HashMap<>();

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        if (bookingEntity == null || bookingEntity.getFlight() == null)
            throw new NullPointerException("Booking or flight cannot be null");
        boolean isBookingExists = isActiveBookingForFlight(bookingEntity);
        boolean isInsufficientSeats = hasInsufficientSeats(bookingEntity);
        if (isBookingExists && isInsufficientSeats)
            throw new AlreadyExistsException("This booking already exists or there are insufficient seats available.");
        bookingEntity.getFlight().setAvailableSeats(bookingEntity.getFlight().getAvailableSeats() - bookingEntity.getPassengers().size());
        return BOOKINGS.put(bookingEntity.getId(), bookingEntity);
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return BOOKINGS.values();
    }

    @Override
    public Optional<BookingEntity> getById(String id) {
        return Optional.ofNullable(BOOKINGS.get(id));
    }

    @Override
    public BookingEntity deleteById(String id) {
        return BOOKINGS.remove(id);
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        if(bookingEntity == null) throw new NullPointerException();
        if(!existsById(bookingEntity.getId())) throw new BookingNotFoundException("There is no booking with this id: " + bookingEntity.getId());
        return BOOKINGS.put(bookingEntity.getId(), bookingEntity);
    }

    @Override
    public boolean existsById(String id) {
        return BOOKINGS.get(id) != null;
    }

    private boolean isActiveBookingForFlight(BookingEntity booking) {
        return BOOKINGS.values().stream()
                .anyMatch(existingBooking -> existingBooking.getFlight().equals(booking.getFlight()) && existingBooking.isActive());
    }

    private boolean hasInsufficientSeats(BookingEntity booking) {
        return booking.getFlight().getAvailableSeats() < booking.getPassengers().size();
    }
}
