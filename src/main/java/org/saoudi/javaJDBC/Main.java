package org.saoudi.javaJDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<User> users = new ArrayList<>();
        try{
            Connection connexion = DatabaseManager.getConnection();
            Statement statement = connexion.createStatement();

            // Exécuter une requête
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            // Parcourir les résultats de la requête
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user");
                String pwd = resultSet.getString("password");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                User tempUser = new User(id,userName,pwd,role);
                users.add(tempUser);
                System.out.println(tempUser);

            }

            resultSet.close();
            statement.close();
            connexion.close();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}