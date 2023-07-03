package org.example;



public class User {

    public static enum Role{
        ADMIN,
        USER,
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    private int id;
    private String user;
    private String password;
    private Role role;

    public User(){
        this(0,"","",Role.USER);
    }
    public User(int id, String user,String password,Role role){
        this.id = id;
        this.user = user;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
