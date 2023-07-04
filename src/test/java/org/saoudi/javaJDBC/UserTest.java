package org.saoudi.javaJDBC;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class UserTest {

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User(1, "dodo", "kiki@example.com", "password", User.Role.USER);
        User user2 = new User(2, "fafa", "fafa@example.com", "1234", User.Role.USER);
        User user3 = new User(1, "kiki", "kiki@example.com", "azerty", User.Role.ADMIN);

        // Vérifier l'égalité entre deux utilisateurs ayant les mêmes adresses email
        assertEquals(user1, user3);
        assertEquals(user1.hashCode(), user3.hashCode());

        // Vérifier la différence entre deux utilisateurs ayant des adresses email différentes
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testToString() {
        User user1 = new User(1, "dodo", "kiki@example.com", "password", User.Role.USER);
        String expectedString = "User{id=1, userName='dodo', email='kiki@example.com', password='password', role=USER}";

        assertEquals(expectedString, user1.toString());
    }
}