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
}
