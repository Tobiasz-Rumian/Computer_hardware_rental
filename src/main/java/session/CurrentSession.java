package session;

import data.User;
import enums.Role;

import java.io.Serializable;

/**
 * Created by zekori on 29.04.17.
 */
public class CurrentSession implements Serializable {
    private static CurrentSession ourInstance = new CurrentSession();
    private User loggedUser;

    public static CurrentSession getInstance() {
        return ourInstance;
    }

    private CurrentSession() {
    }

    public void setLoggedUser(User user) {
        loggedUser = user;
    }

    public Role getLoggedUserRole() {
        if (loggedUser == null) return null;
        return loggedUser.getRole();
    }

    public void clearCurrentSession() {
        loggedUser = null;
    }
}
