package repository;

import config.DatabaseConfiguration;
import models.menu.Menu;

import java.sql.*;
import java.util.ArrayList;

public class MenuRepository {
    // CallableStatement
    public void insertMenu(Menu menu) {
        String preparedSql = "{call insertMenu(?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setObject(2, menu.getItemList());

            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added menu with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Menu getMenuById(int id) {
        String selectSql = "SELECT * FROM menus WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToMenu(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateMenuName(int id, String name) {
        String updateNameSql = "UPDATE menus SET name=? WHERE id=?";

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

    private Menu mapToMenu(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Menu(
                resultSet.getInt(1),
                resultSet.getString(2),
                (ArrayList)resultSet.getObject(3)
            );
        }
        return null;
    }
    public void deleteMenuWithId(int id){
        String deleteMenuWithId = "DELETE FROM menus WHERE id=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteMenuWithId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
