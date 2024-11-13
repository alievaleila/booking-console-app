package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingInDatabase extends BookingDao {
    @Override
    public BookingEntity create(BookingEntity booking) {
        return null;
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return List.of();
    }

    @Override
    public Optional<BookingEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        return false;
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
