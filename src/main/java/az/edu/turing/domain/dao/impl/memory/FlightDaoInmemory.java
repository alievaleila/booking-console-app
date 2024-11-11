package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FlightDaoInmemory extends FlightDao {
    private final Map<String, FlightEntity> flightDatabase = new HashMap<>();

    @Override
    public FlightEntity create(FlightEntity flightEntity) {
        flightDatabase.put(flightEntity.getId(), flightEntity);
        return flightEntity;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        return flightDatabase.values();
    }

    @Override
    public Optional<FlightEntity> getById(String id) {
        return Optional.ofNullable(flightDatabase.get(id));
    }

    @Override
    public FlightEntity deleteById(String id) {
        return flightDatabase.remove(id);
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        if (flightDatabase.containsKey(flightEntity.getId())) {
            flightDatabase.put(flightEntity.getId(), flightEntity);
            return flightEntity;
        }
        return null;
    }
}
