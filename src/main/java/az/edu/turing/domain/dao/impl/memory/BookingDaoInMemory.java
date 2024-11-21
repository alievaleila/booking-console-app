package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.*;

public class BookingDaoInMemory extends BookingDao {

    public static final Map<Long, BookingEntity> BOOKINGS = new HashMap<>();

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        bookingEntity.getFlight().setAvailableSeats(bookingEntity.getFlight().getAvailableSeats() - bookingEntity.getPassengers().size());
        return BOOKINGS.put(bookingEntity.getId(), bookingEntity);
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return BOOKINGS.values();
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        return Optional.ofNullable(BOOKINGS.get(id));
    }

    @Override
    public BookingEntity deleteById(Long id) {
        BookingEntity bookingEntity = BOOKINGS.get(id);
        bookingEntity.setActive(false);
        return update(bookingEntity);
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return BOOKINGS.put(bookingEntity.getId(), bookingEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return BOOKINGS.values().stream()
                .anyMatch(that -> that.getId().equals(id));
    }

    @Override
    public List<String> findMyFlightsByNameAndSurname(String name, String surname) {
        List<String> flights = new ArrayList<>();

        BOOKINGS.values().stream()
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
                    flights.add(flightInfo);
                });

        if (flights.isEmpty()) {
            System.out.println("No flights found for the passenger.");
        }

        return flights;
    }

}
