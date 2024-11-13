package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.util.InputUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingInFile extends BookingDao {
    private final FileUtil<BookingEntity> fileUtil;
    private final InputUtil inputUtil;

    public BookingInFile(FileUtil<BookingEntity> fileUtil, InputUtil inputUtil) {
        this.fileUtil = fileUtil;
        this.inputUtil = inputUtil;
    }

    @Override
    public boolean existsById(String bookingId) {
        List<BookingEntity> entityList = fileUtil.readObjectFromFile();

        return entityList.stream().anyMatch(bookingEntity -> bookingEntity.getId().equals(bookingId));
    }

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        List<BookingEntity> bookingEntityList = fileUtil.readObjectFromFile();
        bookingEntityList.add(bookingEntity);
        fileUtil.writeObjectToFile(bookingEntityList);
        return bookingEntity;
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return fileUtil.readObjectFromFile();
    }

    @Override
    public Optional<BookingEntity> getById(String bookingId) {
        List<BookingEntity> entityList = fileUtil.readObjectFromFile();
        for (BookingEntity bookingEntity : entityList) {
            if (bookingEntity.getId().equals(bookingId)) {
                return Optional.of(bookingEntity);
            }
        }
        return Optional.empty();
    }

    @Override
    public BookingEntity deleteById(String bookingId) {
        List<BookingEntity> entityList = fileUtil.readObjectFromFile();
        for (BookingEntity bookingEntity : entityList) {
            if (bookingEntity.getId().equals(bookingId)) {
                entityList.remove(bookingEntity);
                fileUtil.writeObjectToFile(entityList);
            }
            return bookingEntity;
        }
        return null;
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        List<BookingEntity> bookingEntityList = fileUtil.readObjectFromFile();
        for (BookingEntity entity : bookingEntityList) {
            if (entity.getId().equals(bookingEntity.getId())) {
                entity.setActive(inputUtil.getBoolean(
                        Boolean.valueOf("Is this booking active? (true for no, false for yes): ")));
                entity.setFlight(inputUtil.getFlightEntity("Enter the new flight: "));

            }
            fileUtil.writeObjectToFile(bookingEntityList);
        }
        return bookingEntity;
    }
}



