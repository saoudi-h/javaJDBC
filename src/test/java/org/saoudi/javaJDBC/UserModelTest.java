package org.saoudi.javaJDBC;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;

public class UserModelTest {
    private UserModel userModel = new UserModel();;
    private User user;
    private User user2;


    @AfterEach
    public void cleanup() throws SQLException {
        if (user != null)
            userModel.delete(user);
        if (user2 != null)
            userModel.delete(user2);
    }

    @Test
    public void testFind() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        userModel.create(user);
        user2 = userModel.find(user.getId());
        assertNotNull(user2);
        assertEquals(user, user2);
        user2 = userModel.find("momo@java.fr");
        assertNotNull(user2);
        assertEquals(user, user2);
        user2 = userModel.find(2);
        assertNull(user2);
    }

    @Test
    public void testFindAll() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        user2 = new User(2, "jiji", "jiji@java.fr", "password456", User.Role.USER);
        userModel.create(user);
        userModel.create(user2);
        User[] users = userModel.findAll();
        assertEquals(2, users.length);
        assertEquals(user, users[0]);
        assertEquals(user2, users[1]);
    }

    @Test
    public void testCreate() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        user2 = userModel.create(user);
        assertNotNull(user2);
        assertNotEquals(1, user2.getId());
        assertEquals(user.getUserName(), user2.getUserName());
        assertEquals(user.getEmail(), user2.getEmail());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getRole(), user2.getRole());
    }

    @Test
    public void testUpdate() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        userModel.create(user);
        user.setUserName("momo fufu");
        user.setEmail("momofufu@java.fr");
        user.setPassword("newpassword");
        user.setRole(User.Role.ADMIN);
        userModel.update(user);
        user2 = userModel.find(user.getId());
        assertNotNull(user2);
        assertEquals(user, user2);
    }

    @Test
    public void testDelete() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        userModel.create(user);
        boolean deleted = userModel.delete(user);
        assertTrue(deleted);
        User deletedUser = userModel.find(1);
        assertNull(deletedUser);
    }

    @Test
    public void testSave() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        user2 = userModel.save(user);
        assertNotNull(user2);
        assertNotEquals(1, user2.getId());
        assertEquals(user.getUserName(), user2.getUserName());
        assertEquals(user.getEmail(), user2.getEmail());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getRole(), user2.getRole());
    }

    @Test
    public void testExists() throws SQLException {
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        userModel.create(user);
        boolean existsByEmail = userModel.exists("momo@java.fr");
        assertTrue(existsByEmail);
        boolean existsById = userModel.exists(user.getId());
        assertTrue(existsById);
        boolean existsByInvalidId = userModel.exists(-6);
        assertFalse(existsByInvalidId);
    }

    @Test
    public void testCount() throws SQLException {
        int initialCount = userModel.count();
        user = new User(1, "momo", "momo@java.fr", "azerty1991", User.Role.USER);
        user2 = new User(2, "jiji", "jiji@java.fr", "password456", User.Role.USER);
        userModel.create(user);
        userModel.create(user2);
        int updatedCount = userModel.count();
        assertEquals(initialCount + 2, updatedCount);
    }
}