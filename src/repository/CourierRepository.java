package repository;

import config.DatabaseConfiguration;
import models.courier.Courier;

import java.sql.*;

public class CourierRepository {
    // CallableStatement
    public void insertCourier(Courier courier) {
        String preparedSql = "{call insertCourier(?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, courier.getName());
            cstmt.setString(3, courier.getPhoneNumber());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added courier with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Courier getPlaceById(int id) {
        String selectSql = "SELECT * FROM couriers WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCourier(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateCourierName(int id, String name) {
        String updateNameSql = "UPDATE couriers SET name=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Courier mapToCourier(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Courier(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3)
            );
        }
        return null;
    }
    public void deleteCourierWithId(int id){
        String deleteCourierWithId = "DELETE FROM couriers WHERE id=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteCourierWithId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
