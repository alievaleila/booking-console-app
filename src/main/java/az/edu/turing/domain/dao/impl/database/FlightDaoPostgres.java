package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlightDaoPostgres extends FlightDao {

    ConnectionHelper connectionHelper;

    public FlightDaoPostgres(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        String createTableQuery = "CREATE TABLE IF NOT EXISTS flights (" +
                "id UUID PRIMARY KEY NOT NULL," +
                "departure_point VARCHAR(255) NOT NULL," +
                "destination_point VARCHAR(255) NOT NULL," +
                "flight_number int NOT NULL," +
                "departure_time TIMESTAMP(3) NOT NULL," +
                "total_seats int NOT NULL," +
                "available_seats int NOT NULL);";

        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(createTableQuery)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FlightEntity create(FlightEntity flightEntity) {
        String query = "INSERT INTO flights VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING *";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, flightEntity.getId());
            ps.setString(2, flightEntity.getDeparturePoint());
            ps.setString(3, flightEntity.getDestinationPoint());
            ps.setInt(4, flightEntity.getFlightNumber());
            ps.setTimestamp(5, Timestamp.valueOf(flightEntity.getDepartureTime()));
            ps.setInt(6, flightEntity.getTotalSeats());
            ps.setInt(7, flightEntity.getAvailableSeats());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToFlightEntity(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FlightEntity mapResultSetToFlightEntity(ResultSet rs) throws SQLException {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(rs.getObject("id", UUID.class));
        flightEntity.setDeparturePoint(rs.getString("departure_point"));
        flightEntity.setDestinationPoint(rs.getString("destination_point"));
        flightEntity.setFlightNumber(rs.getInt("flight_number"));
        flightEntity.setTotalSeats(rs.getInt("total_seats"));
        flightEntity.setAvailableSeats(rs.getInt("available_seats"));
        return flightEntity;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        return List.of();
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
