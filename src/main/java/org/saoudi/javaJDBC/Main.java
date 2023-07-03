package org.saoudi.javaJDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties appProps = new Properties();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println(rootPath);
        appProps.load(new FileInputStream("config.properties"));
        String database = appProps.getProperty("mysql_database");
        System.out.println(database);
        String url = appProps.getProperty("mysql_url")+database;
        String user = appProps.getProperty("mysql_user");
        String password = appProps.getProperty("mysql_password");
//        String url = "jdbc:mysql://localhost:3306/java_jdbc";
//        String user = "root";
//        String pwd = "";
        ArrayList<User> users = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connexion = DriverManager.getConnection(url,user,password);

            Statement statement = connexion.createStatement();

            // Exécuter une requête
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            // Parcourir les résultats de la requête
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user");
                String pwd = resultSet.getString("password");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                // Faites ce que vous souhaitez avec les données récupérées
//                System.out.println("ID : " + id + ", user : " + userName);
                User tempUser = new User(id,userName,pwd,role);
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