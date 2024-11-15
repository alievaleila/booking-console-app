package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PassengerDaoInMemory extends PassengerDao {

    public static final List<PassengerEntity> PASSENGERS = new ArrayList<>();

    @Override
    public PassengerEntity create(PassengerEntity passengerEntity) {
        PASSENGERS.add(passengerEntity);
        return passengerEntity;
    }

    @Override
    public Collection<PassengerEntity> getAll() {
        return null;
    }

    @Override
    public Optional<PassengerEntity> getById(Long id) {
        return PASSENGERS.stream()
                .filter(passengerEntity -> passengerEntity.getId().equals(id))
                .findFirst();
    }

    @Override
    public PassengerEntity deleteById(Long id) {
        return null;
    }

    @Override
    public PassengerEntity update(PassengerEntity passengerEntity) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return PASSENGERS.stream()
                .anyMatch(passengerEntity -> passengerEntity.getId().equals(id));
    }

    @Override
    public List<PassengerEntity> getPassengersByBookingId(Long bookingId) {
        return List.of();
    }
}
