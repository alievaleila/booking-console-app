package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.List;

public abstract class BookingDao implements Dao<BookingEntity, Long> {

    public abstract boolean existsById(Long id);

    public abstract List<String> findMyFlightsByNameAndSurname(String name, String surname);
}
