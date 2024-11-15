package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.util.InputUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PassengerInFile extends PassengerDao {

    private final FileUtil<PassengerEntity> fileUtil;
    private final InputUtil inputUtil;


    public PassengerInFile(FileUtil<PassengerEntity> fileUtil, InputUtil inputUtil) {
        this.fileUtil = fileUtil;
        this.inputUtil = inputUtil;
    }

    @Override
    public PassengerEntity create(PassengerEntity passengerEntity) {
        List<PassengerEntity> passengerEntityList = fileUtil.readObjectFromFile();
        passengerEntityList.add(passengerEntity);
        fileUtil.writeObjectToFile(passengerEntityList);
        return passengerEntity;
    }

    @Override
    public Collection<PassengerEntity> getAll() {
        return fileUtil.readObjectFromFile();
    }

    @Override
    public Optional<PassengerEntity> getById(Long passengerId) {
        List<PassengerEntity> entityList = fileUtil.readObjectFromFile();
        for (PassengerEntity passengerEntity : entityList) {
            if (passengerEntity.getId().equals(passengerId)) {
                return Optional.of(passengerEntity);
            }
        }
        return Optional.empty();
    }

    @Override
    public PassengerEntity deleteById(Long passengerId) {
        List<PassengerEntity> entityList = fileUtil.readObjectFromFile();
        for (PassengerEntity passengerEntity : entityList) {
            if (passengerEntity.getId().equals(passengerId)) {
                entityList.remove(passengerEntity);
                fileUtil.writeObjectToFile(entityList);
            }
            return passengerEntity;
        }
        return null;
    }

    @Override
    public PassengerEntity update(PassengerEntity passengerEntity) {
        List<PassengerEntity> passengerEntityList = fileUtil.readObjectFromFile();
        for (PassengerEntity entity : passengerEntityList) {
            if (entity.getId().equals(passengerEntity.getId())) {
                entity.setName(inputUtil.getString("Enter the new Passenger name"));
                entity.setSurname(inputUtil.getString("Enter the new Passenger surname"));
            }
            fileUtil.writeObjectToFile(passengerEntityList);
        }
        return passengerEntity;
    }

    @Override
    public boolean existsById(Long passengerId) {
        List<PassengerEntity> entityList = fileUtil.readObjectFromFile();
        return entityList.stream().anyMatch(passengerEntity -> passengerEntity.getId().equals(passengerId));
    }

    @Override
    public List<PassengerEntity> getPassengersByBookingId(Long bookingId) {
        return List.of();
    }
}