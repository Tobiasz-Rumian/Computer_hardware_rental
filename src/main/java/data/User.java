package data;

import enums.Role;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
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
