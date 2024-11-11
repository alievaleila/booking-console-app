package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.Collection;
import java.util.Optional;

public class PassengerDaoInmemory extends PassengerDao {
    @Override
    public PassengerEntity create(PassengerEntity passengerEntity) {
        return null;
    }

    @Override
    public Collection<PassengerEntity> getAll() {
        return null;
    }

    @Override
    public Optional<PassengerEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public PassengerEntity deleteById(String id) {
        return null;
    }

    @Override
    public PassengerEntity update(PassengerEntity passengerEntity) {
        return null;
    }
}
