package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookingDaoInMemory extends BookingDao {

    private static final Map<Long, BookingEntity> BOOKINGS = new HashMap<>();

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        bookingEntity.getFlight().setAvailableSeats(bookingEntity.getFlight().getAvailableSeats() - bookingEntity.getPassengers().size());
        return BOOKINGS.put(bookingEntity.getId(), bookingEntity);
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return BOOKINGS.values();
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        return Optional.ofNullable(BOOKINGS.get(id));
    }

    @Override
    public BookingEntity deleteById(Long id) {
        BookingEntity bookingEntity = BOOKINGS.get(id);
        bookingEntity.setActive(false);
        return update(bookingEntity);
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return BOOKINGS.put(bookingEntity.getId(), bookingEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return BOOKINGS.values().stream()
                .anyMatch(that -> that.getId().equals(id));
    }
}
