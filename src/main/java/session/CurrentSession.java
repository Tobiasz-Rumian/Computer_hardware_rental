package session;

import data.User;
import enums.Role;

/**
 * Created by zekori on 29.04.17.
 */
public class CurrentSession {
    private static CurrentSession ourInstance = new CurrentSession();
    private User loggedUser;
    public static CurrentSession getInstance() {
        return ourInstance;
    }

    private CurrentSession() {
    }
    public CurrentSession(User user){
        loggedUser=user;
    }

    public Role getLoggedUserRole() {
        return loggedUser.getRole();
    }
}
