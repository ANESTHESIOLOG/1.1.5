package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = Util.connectionOpen();

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name_i VARCHAR(40), " +
                    "lastname_i VARCHAR(40), " +
                    "age_i TINYINT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String a = "INSERT INTO users (name_i, lastname_i, age_i) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(a)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String a = "DELETE FROM users WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(a)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() {
        List<User> List = new ArrayList<>();
        String a = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(a)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name_i"));
                user.setLastName(rs.getString("lastname_i"));
                user.setAge(rs.getByte("age_i"));
                System.out.println(user);
                List.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE anum.users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

