package org.saoudi.javaJDBC;

import java.util.Objects;

/**
 * Classe représentant un utilisateur.
 */
public class User {


    /**
     * Rôles possibles d'un utilisateur.
     */
    public static enum Role {
        ADMIN,
        USER,
    }

    private int id;
    private String userName;
    private String email;
    private String password;
    private Role role;

    /**
     * Constructeur par défaut de la classe User.
     */
    public User() {
        this(0, "", "","", Role.USER);
    }

    /**
     * Constructeur de la classe User.
     *
     * @param id       l'identifiant de l'utilisateur
     * @param user     le nom d'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @param role     le rôle de l'utilisateur
     */
    public User(int id, String userName,String email, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}