package org.saoudi.javaJDBC;

import java.sql.*;
import java.util.ArrayList;

public class UserModel {
    private Connection connection;

    public UserModel() {
        connection = DatabaseManager.getConnection();
    }

    public User findOne(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String username = resultSet.getString("user");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            User.Role userRole = User.Role.valueOf(role);

            return new User(userId, username, password, userRole);
        } else return null;
    }

    public User[] findAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user where 1";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String username = resultSet.getString("user");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            User.Role userRole = User.Role.valueOf(role);

            users.add(new User(userId, username, password, userRole));
        }
        return users.toArray(new User[0]);
    }

    public User create(User user) throws SQLException {
        String sql = "INSERT INTO user (user, password, role) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUser());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole().name());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            user.setId(generatedId);
        } else {
            throw new SQLException("La création de l'utilisateur a échoué");
        }
        return user;
    }

    public void update(User user) throws SQLException {
        String sql = "Update user set user=?, password=?, role=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUser());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole().name());
        statement.setInt(4, user.getId());
        statement.executeUpdate();
    }

    public boolean delete(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        int res = statement.executeUpdate();
        return res==1;
    }
}

