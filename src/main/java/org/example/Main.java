package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/java_jdbc";
        String user = "root";
        String pwd = "";
        ArrayList<User> users = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connexion = DriverManager.getConnection(url,user,pwd);

            Statement statement = connexion.createStatement();

            // Exécuter une requête
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            // Parcourir les résultats de la requête
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user");
                String password = resultSet.getString("password");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                // Faites ce que vous souhaitez avec les données récupérées
//                System.out.println("ID : " + id + ", user : " + userName);
                User tempUser = new User(id,userName,password,role);
                users.add(tempUser);
                System.out.println(tempUser);

            }

            // Fermer les ressources
            resultSet.close();
            statement.close();
            connexion.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}