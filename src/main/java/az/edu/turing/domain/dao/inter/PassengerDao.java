package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.List;

public abstract class PassengerDao implements Dao<PassengerEntity, Long> {

    public abstract boolean existsById(Long id);

    public abstract List<PassengerEntity> getPassengersByBookingId(Long bookingId);
}
