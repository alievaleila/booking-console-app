package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.util.InputUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlightInFile extends FlightDao {

    private final FileUtil<FlightEntity> fileUtil;
    private final InputUtil inputUtil;

    public FlightInFile(FileUtil<FlightEntity> fileUtil, InputUtil inputUtil) {
        this.fileUtil = fileUtil;
        this.inputUtil = inputUtil;
    }

    @Override
    public FlightEntity create(FlightEntity flightEntity) {
        List<FlightEntity> flightEntityList = fileUtil.readObjectFromFile();
        flightEntityList.add(flightEntity);
        fileUtil.writeObjectToFile(flightEntityList);
        return flightEntity;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        return fileUtil.readObjectFromFile();
    }

    @Override
    public Optional<FlightEntity> getById(UUID id) {
        List<FlightEntity> entityList = fileUtil.readObjectFromFile();
        for (FlightEntity flightEntity : entityList) {
            if (flightEntity.getId().equals(id)) {
                return Optional.of(flightEntity);
            }
        }
        return Optional.empty();

    }

    @Override
    public FlightEntity deleteById(UUID id) {
        List<FlightEntity> entityList = fileUtil.readObjectFromFile();
        for (FlightEntity flightEntity : entityList) {
            if (flightEntity.getId().equals(id)) {
                entityList.remove(flightEntity);
                fileUtil.writeObjectToFile(entityList);
                return flightEntity;
            }
        }
        return null;
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        List<FlightEntity> flightEntityList = fileUtil.readObjectFromFile();
        for (FlightEntity entity : flightEntityList) {
            if (entity.getId().equals(flightEntity.getId())) {
                entity.setDeparturePoint(inputUtil.getString("Enter the new Departure Point"));
                entity.setDestinationPoint(inputUtil.getString("Enter the new Destination Point"));
                entity.setAvailableSeats(inputUtil.getInteger("Enter the available seats"));
                entity.setDestinationPoint(inputUtil.getString("Enter the new Destination Point"));
                entity.setTotalSeats(inputUtil.getInteger("Enter the total seats"));
                entity.setDepartureTime(inputUtil.getLocalDateTime("Enter the new departure time"));
            }
        }
        fileUtil.writeObjectToFile(flightEntityList);
        return flightEntity;
    }

    @Override
    public boolean existsById(UUID flightId) {
        List<FlightEntity> entityList = fileUtil.readObjectFromFile();
        return entityList.stream().anyMatch(flightEntity -> flightEntity.getId().equals(flightId));
    }

    @Override
    public Optional<FlightEntity> getByFlightNumber(int flightNumber) {
        List<FlightEntity> entityList = fileUtil.readObjectFromFile();
        for (FlightEntity flightEntity : entityList) {
            if (flightEntity.getFlightNumber() == flightNumber) {
                return Optional.of(flightEntity);
            }
        }
        return Optional.empty();
    }
}