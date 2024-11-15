package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.BookingEntity;

public abstract class BookingDao implements Dao<BookingEntity, Long> {

    public abstract boolean existsById(Long id);
}
