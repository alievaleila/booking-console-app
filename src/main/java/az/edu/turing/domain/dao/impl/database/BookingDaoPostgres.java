package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.FlightNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingDaoPostgres extends BookingDao {

    private final ConnectionHelper connectionHelper;

    public BookingDaoPostgres(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        String createTableQuery = "CREATE TABLE IF NOT EXISTS bookings (" +
                "id BIGSERIAL PRIMARY KEY," +
                "flight_id BIGINT NOT NULL," +
                "passenger_id BIGINT NOT NULL," +
                "isActive BOOLEAN NOT NULL DEFAULT true," +
                "FOREIGN KEY (flight_id) REFERENCES flights(id)," +
                "FOREIGN KEY (passenger_id) REFERENCES passengers(id));";

        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(createTableQuery)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookingEntity create(BookingEntity booking) {
        String query = "INSERT INTO bookings (id, flight_id, passenger_id, isActive) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, booking.getId());
            ps.setString(2, booking.getFlight().getId().toString());
            ps.setBoolean(4, booking.isActive());

            for (PassengerEntity passenger : booking.getPassengers()) {
                ps.setLong(3, passenger.getId());
                int rowInsert = ps.executeUpdate();
                System.out.println(rowInsert + " row affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public Collection<BookingEntity> getAll() {
        List<BookingEntity> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Connection connection = connectionHelper.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                bookings.add(mapResultSetToBookingEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        String query = "SELECT * FROM bookings WHERE id =" + id;
        try (Connection connection = connectionHelper.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return Optional.of(mapResultSetToBookingEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        Optional<BookingEntity> bookingEntity = getById(id);
        return bookingEntity.isPresent();
    }

    @Override
    public BookingEntity deleteById(Long id) {
        Optional<BookingEntity> booking = getById(id);
        if (booking.isPresent()) {
            String query = "DELETE FROM flights WHERE id =" + id;
            try (Connection connection = connectionHelper.getConnection();
                 PreparedStatement ps = connection.prepareStatement(query)) {

                int rowInsert = ps.executeUpdate();
                System.out.println(rowInsert + " row affected!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return booking.get();
        } else {
            System.out.println("Booking is not found with " + id);
            return booking.get();
        }
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        String query = "UPDATE bookings SET flight_id = ?, isActive = ? WHERE id = ?";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, bookingEntity.getFlight().getId().toString());
            ps.setBoolean(2, bookingEntity.isActive());
            ps.setString(3, bookingEntity.getId().toString());

            int rowUpdate = ps.executeUpdate();
            System.out.println(rowUpdate + " row affected!");

            return bookingEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BookingEntity mapResultSetToBookingEntity(ResultSet rs) throws SQLException {

        Long flightId = rs.getLong("flight_id");
        FlightDao flightDao = new FlightDaoPostgres(connectionHelper);
        FlightEntity flight = flightDao.getById(flightId).orElseThrow(FlightNotFoundException::new);

        Long bookingId = rs.getLong("id");
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setId(bookingId);
        bookingEntity.setFlight(flight);
        bookingEntity.setActive(rs.getBoolean("isActive"));

        PassengerDaoPostgres passengerDao = new PassengerDaoPostgres(connectionHelper);
        List<PassengerEntity> passengers = passengerDao.getPassengersByBookingId(bookingId);

        bookingEntity.setPassengers(passengers);
        return bookingEntity;
    }
}
