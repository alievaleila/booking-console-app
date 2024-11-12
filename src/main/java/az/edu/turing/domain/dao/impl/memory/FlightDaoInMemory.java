package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Collection;
import java.util.Optional;

public class FlightDaoInMemory extends FlightDao {
    @Override
    public FlightEntity save(FlightEntity flightEntity) {
        return null;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        return null;
    }

    @Override
    public Optional<FlightEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public FlightEntity deleteById(String id) {
        return null;
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        return null;
    }
}
