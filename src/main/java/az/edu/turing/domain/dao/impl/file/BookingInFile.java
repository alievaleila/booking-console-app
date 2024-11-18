package az.edu.turing.domain.dao.impl.file;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.util.InputUtil;

import java.util.ArrayList;
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
    public boolean existsById(Long bookingId) {
        List<BookingEntity> entityList = fileUtil.readObjectFromFile();

        return entityList.stream().anyMatch(bookingEntity -> bookingEntity.getId().equals(bookingId));
    }

    @Override
    public List<String> findMyFlightsByNameAndSurname(String name, String surname) {

        List<String> bookings = new ArrayList<>();
        List<BookingEntity> myBookings = fileUtil.readObjectFromFile();

        myBookings.stream()
                .filter(booking -> booking.getPassengers().stream()
                        .anyMatch(passenger -> passenger.getName().equalsIgnoreCase(name) &&
                                passenger.getSurname().equalsIgnoreCase(surname)))
                .forEach(booking -> {
                    String flightInfo = String.format(
                            "Departure: %s, Destination: %s, " +
                                    "Departure Time: %s,  Total Seats: %d, Available Seats: %d",
                            booking.getFlight().getDeparturePoint(),
                            booking.getFlight().getDestinationPoint(),
                            booking.getFlight().getDepartureTime(),
                            booking.getFlight().getTotalSeats(),
                            booking.getFlight().getAvailableSeats()
                    );
                    bookings.add(flightInfo);
                });

        return bookings;
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
    public Optional<BookingEntity> getById(Long bookingId) {
        List<BookingEntity> entityList = fileUtil.readObjectFromFile();
        for (BookingEntity bookingEntity : entityList) {
            if (bookingEntity.getId().equals(bookingId)) {
                return Optional.of(bookingEntity);
            }
        }
        return Optional.empty();
    }

    @Override
    public BookingEntity deleteById(Long bookingId) {
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



