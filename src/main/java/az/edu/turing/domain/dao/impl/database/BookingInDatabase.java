package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingInDatabase extends BookingDao {
    private final ConnectionHelper connectionHelper;

    public BookingInDatabase(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        String createTableQuery1 =
                "CREATE TABLE IF NOT EXISTS Books(" +
                        "book_id UUID PRIMARY KEY," +
                        "flight_id UUID NOT NULL," +
                        "is_active BOOLEAN DEFAULT TRUE," +
                        "FOREIGN KEY (flight_id) REFERENCES Flights(flight_id));";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(createTableQuery1)) {
            int i = ps.executeUpdate();
            System.out.println(i + " Book table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String createTableQuery2 =
                "CREATE TABLE IF NOT EXISTS BookingPassenger(" +
                        "booking_id UUID NOT NULL," +
                        "passenger_id UUID NOT NULL," +
                        "PRIMARY KEY (booking_id, passenger_id)," +
                        "FOREIGN KEY (booking_id) REFERENCES Books(book_id)," +
                        "FOREIGN KEY (passenger_id) REFERENCES Passengers(passenger_id));";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(createTableQuery2)) {
            int i = ps.executeUpdate();
            System.out.println(i + " Booking Passenger table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookingEntity create(BookingEntity booking) {
        return null;
    }

    @Override
    public Collection<BookingEntity> getAll() {
        return List.of();
    }

    @Override
    public Optional<BookingEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public BookingEntity deleteById(String id) {
        return null;
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return null;
    }
}
