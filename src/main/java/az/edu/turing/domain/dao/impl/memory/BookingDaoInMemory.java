package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.Collection;
import java.util.Optional;

public class BookingDaoInMemory extends BookingDao {

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        return null;
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return null;
    }

    @Override
    public Optional<BookingEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public BookingEntity deleteById(String id) {
        return null;
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return null;
    }
}
