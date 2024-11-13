package az.edu.turing.domain.dao.impl.database;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PassengerDaoPostgres extends PassengerDao {

    private final ConnectionHelper connectionHelper;

    public PassengerDaoPostgres(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public PassengerEntity create(PassengerEntity passengerEntity) {
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into passenger(id,passengerName,passengerSurname) values(?,?,?);");
            preparedStatement.setString(1, passengerEntity.getId());
            preparedStatement.setString(2, passengerEntity.getName());
            preparedStatement.setString(3, passengerEntity.getSurname());
            int rowInsert = preparedStatement.executeUpdate();
            System.out.println(rowInsert + " Passenger has been inserted succesfuly!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerEntity;
    }

    @Override
    public Collection<PassengerEntity> getAll() {
        List<PassengerEntity> list = new ArrayList<>();
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from passenger;");
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
    public Optional<PassengerEntity> getById(String passengerId) {
        Optional<PassengerEntity> passengerEntity = Optional.empty();
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Passengers WHERE passenger_id = ?;");
            preparedStatement.setString(1, passengerId);
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
    public PassengerEntity deleteById(String passengerId) {
        Optional<PassengerEntity> passengerEntity = getById(passengerId);
        if (passengerEntity.isPresent()) {
            try (Connection connection = connectionHelper.getConnection()) {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from passenger where passengerId=?;");
                preparedStatement.setString(1, passengerId);
                int rowInsert = preparedStatement.executeUpdate();
                System.out.println(rowInsert + " Passenger has been deleted succesfuly!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return passengerEntity.get();
        } else {
            System.out.println("Passenger don't found with " + passengerId);
            return passengerEntity.get();
        }
    }

    @Override
    public PassengerEntity update(PassengerEntity passengerEntity) {
        try (Connection connection = connectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update passenger set name=?,surname=? where passengerId=?;");
            preparedStatement.setString(1, passengerEntity.getName());
            preparedStatement.setString(2, passengerEntity.getSurname());
            preparedStatement.setString(3, passengerEntity.getId());
            int rowInsert = preparedStatement.executeUpdate();
            System.out.println(rowInsert + " Passenger has been updated succesfuly!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerEntity;
    }

    @Override
    public boolean existsById(String passengerId) {
        Optional<PassengerEntity> passengerEntity = getById(passengerId);
        return passengerEntity.isPresent();
    }

    @Override
    public List<PassengerEntity> getPassengersByBookingId(UUID bookingId) {
        List<PassengerEntity> passengers = new ArrayList<>();
        String query = "SELECT * FROM passengers WHERE booking_id = ?";
        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, bookingId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String passengerId = rs.getString("id");
                PassengerEntity passenger = new PassengerEntity(passengerId);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public PassengerEntity getPassenger(ResultSet resultSet) throws SQLException {
        String passengerId = resultSet.getString("passengerId");
        String passengerName = resultSet.getString("passengerName");
        String passengerSurname = resultSet.getString("passengerSurname");
        return new PassengerEntity(passengerId, passengerName, passengerSurname);
    }
}
