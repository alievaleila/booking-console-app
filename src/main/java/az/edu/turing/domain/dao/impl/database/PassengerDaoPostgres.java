package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PassengerDaoPostgres extends PassengerDao {

    private final ConnectionHelper connectionHelper;

    public PassengerDaoPostgres(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        String createTableQuery =
                "CREATE TABLE IF NOT EXISTS Passengers(" +
                        "passenger_id BIGSERIAL PRIMARY KEY," +
                        "passenger_name VARCHAR(25) NOT NULL," +
                        "passenger_surname VARCHAR(25) NOT NULL)";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(createTableQuery)) {
            int i = ps.executeUpdate();
            System.out.println(i + " Passenger table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PassengerEntity create(PassengerEntity passengerEntity) {
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Passengers(passenger_id, passenger_name, passenger_surname) VALUES(?, ?, ?);");
            preparedStatement.setLong(1, passengerEntity.getId());
            preparedStatement.setString(2, passengerEntity.getName());
            preparedStatement.setString(3, passengerEntity.getSurname());
            int rowInsert = preparedStatement.executeUpdate();
            System.out.println(rowInsert + " Passenger has been inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerEntity;
    }

    @Override
    public Collection<PassengerEntity> getAll() {
        List<PassengerEntity> list = new ArrayList<>();
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Passengers;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getPassenger(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<PassengerEntity> getById(Long passengerId) {
        Optional<PassengerEntity> passengerEntity = Optional.empty();
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Passengers WHERE passenger_id = ?;");
            preparedStatement.setLong(1, passengerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                passengerEntity = Optional.of(getPassenger(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerEntity;
    }

    @Override
    public PassengerEntity deleteById(Long passengerId) {
        Optional<PassengerEntity> passengerEntity = getById(passengerId);
        if (passengerEntity.isPresent()) {
            try (Connection connection = connectionHelper.getConnection()) {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("DELETE FROM Passengers WHERE passenger_id = ?;");
                preparedStatement.setLong(1, passengerId);
                int rowInsert = preparedStatement.executeUpdate();
                System.out.println(rowInsert + " Passenger has been deleted successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return passengerEntity.get();
        } else {
            System.out.println("Passenger not found with ID " + passengerId);
            return null;
        }
    }

    @Override
    public PassengerEntity update(PassengerEntity passengerEntity) {
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE Passengers SET passenger_name = ?, passenger_surname = ? WHERE passenger_id = ?;");
            preparedStatement.setString(1, passengerEntity.getName());
            preparedStatement.setString(2, passengerEntity.getSurname());
            preparedStatement.setLong(3, passengerEntity.getId());
            int rowUpdate = preparedStatement.executeUpdate();
            System.out.println(rowUpdate + " Passenger has been updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerEntity;
    }

    @Override
    public boolean existsById(Long passengerId) {
        Optional<PassengerEntity> passengerEntity = getById(passengerId);
        return passengerEntity.isPresent();
    }

    @Override
    public List<PassengerEntity> getPassengersByBookingId(Long bookingId) {
        List<PassengerEntity> passengers = new ArrayList<>();
        String query = "SELECT * FROM passengers WHERE booking_id = ?";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, bookingId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long passengerId = rs.getLong(  "id");
                PassengerEntity passenger = new PassengerEntity(passengerId);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public PassengerEntity getPassenger(ResultSet resultSet) throws SQLException {
        Long passengerId = resultSet.getLong("passenger_id");
        String passengerName = resultSet.getString("passenger_name");
        String passengerSurname = resultSet.getString("passenger_surname");
        return new PassengerEntity(passengerId, passengerName, passengerSurname);
    }
}

