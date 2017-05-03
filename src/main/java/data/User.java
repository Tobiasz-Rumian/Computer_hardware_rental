package data;

import enums.Role;
import lombok.Data;

import java.io.Serializable;

/**
 * Klasa bazodanowa przechowująca dane użytkownika.
 * @author Tobiasz Rumian
 */
@Data
public class User implements Serializable {
    private String nick;
    private Role role;
    private String password;

    public User(String nick, Role role, String password) {
        this.nick = nick;
        this.role = role;
        this.password = password;
    }

    public String toString() {
        return nick;
    }
}
