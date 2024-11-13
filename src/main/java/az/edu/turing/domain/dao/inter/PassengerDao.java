package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.List;
import java.util.UUID;

public abstract class PassengerDao implements Dao<PassengerEntity, String> {
    public abstract boolean existsById(String id);

    public abstract List<PassengerEntity> getPassengersByBookingId(UUID bookingId);
}
