package org.saoudi.javaJDBC;

import java.sql.*;
import java.util.ArrayList;

/**
 * Cette classe gère les opérations CRUD (Create, Read, Update, Delete) sur la table "user" dans une base de données.
 */
public class UserModel {
    private Connection connection;

    /**
     * Constructeur de la classe UserModel.
     * Initialise la connexion à la base de données.
     */
    public UserModel() {
        connection = DatabaseManager.getConnection();
    }

    /**
     * Recherche et renvoie un utilisateur correspondant à l'identifiant donné.
     *
     * @param id L'identifiant de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public User find(int id) throws SQLException {
        return this.find("id",Integer.toString(id));
    }

    /**
     * Recherche et renvoie un utilisateur correspondant à l'email donné.
     *
     * @param email l'adresse email de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public User find(String email) throws SQLException {
        return this.find("email",email);
    }


    /**
     * Recherche et renvoie un utilisateur correspondant à l'identifiant donné par by dans la valeur est value.
     *
     * @param by l'attribue qui identifie un utilisateur unique.
     * @param value la valeur de l'attribue by.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    private User find(String by,String value) throws SQLException {
        String sql = "SELECT * FROM user WHERE " + by + " = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String userName = resultSet.getString("userName");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            User.Role userRole = User.Role.valueOf(role);

            return new User(userId, userName,email, password, userRole);
        } else return null;
    }


    /**
     * Renvoie tous les utilisateurs de la table "user".
     *
     * @return Un tableau d'utilisateurs.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public User[] findAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user where 1";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String username = resultSet.getString("userName");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            User.Role userRole = User.Role.valueOf(role);

            users.add(new User(userId, username,email, password, userRole));
        }
        return users.toArray(new User[0]);
    }


    /**
     * Crée un nouvel utilisateur dans la table "user".
     *
     * @param user L'utilisateur à créer.
     * @return L'utilisateur créé avec son identifiant mis à jour.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public User create(User user) throws SQLException {
        String sql = "INSERT INTO user (userName,email, password, role) VALUES (?,? ,?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUserName());
        statement.setString(2,user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole().name());
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

    /**
     * Met à jour les informations d'un utilisateur dans la table "user".
     *
     * @param user L'utilisateur à mettre à jour.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public void update(User user) throws SQLException {
        String sql = "Update user set userName=?,email=?, password=?, role=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole().name());
        statement.setInt(5, user.getId());
        statement.executeUpdate();

    }

    /**
     * Supprime un utilisateur de la table "user".
     *
     * @param user L'utilisateur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public boolean delete(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        int res = statement.executeUpdate();
        return res==1;
    }

    /**
     * Enregistre un utilisateur dans la table "user".
     * Effectue une opération d'insertion avec mise à jour si l'utilisateur existe déjà.
     *
     * @param user L'utilisateur à enregistrer.
     * @return true si l'enregistrement a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public User save(User user) throws SQLException {
        String sql = "INSERT INTO user (userName,email, password, role)\n" +
                "VALUES (?,?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE userName = ?,email = ?, password = ?, role = ?";

        PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole().toString());
        statement.setString(5, user.getUserName());
        statement.setString(6, user.getEmail());
        statement.setString(7, user.getPassword());
        statement.setString(8, user.getRole().toString());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            user.setId(generatedId);
        }
        return user;
    }



    /**
     * Vérifie si un utilisateur avec l'email donné existe dans la base de données.
     *
     * @param email L'identifiant de l'utilisateur à vérifier.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public boolean exists(String email) throws SQLException {
        return this.exists("email",email);
    }
    /**
     * Vérifie si un utilisateur avec l'identifiant donné existe dans la base de données.
     *
     * @param id L'identifiant de l'utilisateur à vérifier.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public boolean exists(int id) throws SQLException {
        return this.exists("id",Integer.toString(id));
    }
    /**
     * Vérifie si un utilisateur avec l'identifiant 'by' existe dans la base de données.
     *
     * @param by le nom de l'attribut par lequel on identifiera l'utilisateur à vérifier.
     * @param value la valeur de l'identifiant de l'utilisateur indiqué par by.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    private boolean exists(String by, String value) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM user WHERE "+by+" = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        } else {
            return false;
        }
    }

    /**
     * Compte le nombre total d'utilisateurs dans la base de données.
     *
     * @return Le nombre total d'utilisateurs.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM user";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("count");
        } else {
            return 0;
        }
    }
}

