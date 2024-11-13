package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.UUID;

public abstract class FlightDao implements Dao<FlightEntity, UUID> {
    public abstract boolean  existsById(String id);
}
