package repository;

import config.DatabaseConfiguration;
import models.user.User;

import java.sql.*;

public class UserRepository {

    public void createTable(){
        String createTable = "CREATE TABLE IF NOT EXISTS companies" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(15)," +
                "street varchar(20), number int, city varchar(15), country varchar(15),"+
                "contact_person varchar(20), email varchar(25), contact_number int)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try{
            Statement statement = connection.createStatement();
            statement.execute(createTable);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // CallableStatement
    public void insertUser(User user) {
        String preparedSql = "{call insertUser(?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, user.getName());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public User getUserById(int id) {
        String selectSql = "SELECT * FROM users WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateUserName(int id, String name) {
        String updateNameSql = "UPDATE users SET name=? WHERE id=?";

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

    private User mapToUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new User(
                    resultSet.getString(2)
            );
        }
        return null;
    }
    public void deleteUserWithId(int id){
        String deleteUserWithId = "DELETE FROM users WHERE id=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteUserWithId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
