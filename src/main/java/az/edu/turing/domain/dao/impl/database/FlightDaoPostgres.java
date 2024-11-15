package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightDaoPostgres extends FlightDao {

    private final ConnectionHelper connectionHelper;

    public FlightDaoPostgres(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        String createTableQuery =
                "CREATE TABLE IF NOT EXISTS Flights(" +
                        "flight_id BIGSERIAL PRIMARY KEY," +
                        "flight_departurepoint VARCHAR(255) NOT NULL," +
                        "flight_destinationpoint VARCHAR(255) NOT NULL," +
                        "flight_departuretime TIMESTAMP(3) NOT NULL," +
                        "flight_totalseats int NOT NULL," +
                        "flight_availableseats int NOT NULL);";

        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(createTableQuery)) {
            int i = ps.executeUpdate();
            System.out.println(i + " Flight table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FlightEntity create(FlightEntity flightEntity) {
        String query = "INSERT INTO Flights(flight_id, flight_departurepoint, flight_destinationpoint, " +
                "flight_departuretime, flight_totalseats, flight_availableseats) " +
                "VALUES(?, ?, ?, ?, ?, ?) RETURNING *";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, flightEntity.getId());
            ps.setString(2, flightEntity.getDeparturePoint());
            ps.setString(3, flightEntity.getDestinationPoint());
            ps.setTimestamp(4, Timestamp.valueOf(flightEntity.getDepartureTime()));
            ps.setInt(5, flightEntity.getTotalSeats());
            ps.setInt(6, flightEntity.getAvailableSeats());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToFlightEntity(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<FlightEntity> getAll() {
        List<FlightEntity> flights = new ArrayList<>();
        String query = "SELECT * FROM Flights";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flights.add(mapResultSetToFlightEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        String query = "SELECT * FROM Flights WHERE flight_id = ?";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToFlightEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public FlightEntity deleteById(Long id) {
        String query = "DELETE FROM Flights WHERE flight_id = ? RETURNING *";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToFlightEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FlightEntity update(FlightEntity flightEntity) {
        String query = "UPDATE Flights " +
                "SET flight_departurepoint = ?, flight_destinationpoint = ?, " +
                "flight_departuretime = ?, flight_totalseats = ?, flight_availableseats = ? WHERE flight_id = ? RETURNING *";

        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, flightEntity.getDeparturePoint());
            ps.setString(2, flightEntity.getDestinationPoint());
            ps.setTimestamp(3, Timestamp.valueOf(flightEntity.getDepartureTime()));
            ps.setInt(4, flightEntity.getTotalSeats());
            ps.setInt(5, flightEntity.getAvailableSeats());
            ps.setLong(6, flightEntity.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToFlightEntity(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        Optional<FlightEntity> flightEntity = getById(id);
        return flightEntity.isPresent();
    }

    private FlightEntity mapResultSetToFlightEntity(ResultSet rs) throws SQLException {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(rs.getLong("flight_id"));
        flightEntity.setDeparturePoint(rs.getString("flight_departurepoint"));
        flightEntity.setDestinationPoint(rs.getString("flight_destinationpoint"));
        flightEntity.setTotalSeats(rs.getInt("flight_totalseats"));
        flightEntity.setAvailableSeats(rs.getInt("flight_availableseats"));
        return flightEntity;
    }
}

