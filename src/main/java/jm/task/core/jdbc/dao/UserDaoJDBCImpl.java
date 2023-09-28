package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, UserName VARCHAR(20), LastName VARCHAR(20), Age INT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO users (UserName, LastName, Age) VALUES (?, ?, ?) ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sqlCommand = "SELECT * FROM users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString("UserName"));
                user.setLastName(rs.getString("LastName"));
                user.setAge(rs.getByte("Age"));

                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usersList;
    }

    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
