package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.List;

public abstract class BookingDao implements Dao<BookingEntity,String> {
    public abstract boolean existsById(String id);
}
