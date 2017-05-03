package session;

import data.User;
import enums.Role;

import java.io.Serializable;

/**
 * Obecna sejsa użytkownika.
 */
public class CurrentSession implements Serializable {
    private static CurrentSession ourInstance = new CurrentSession();
    private User loggedUser;

    public static CurrentSession getInstance() {
        return ourInstance;
    }

    private CurrentSession() {
    }

    /**
     * Przypisuje użytkownika do sesji.
     * @param user Przypisywany użytkownik.
     */
    public void setLoggedUser(User user) {
        loggedUser = user;
    }

    /**
     * Zwraca rolę przypisanego użytkownika.
     * @return Rola użytkownika.
     */
    public Role getLoggedUserRole() {
        if (loggedUser == null) return null;
        return loggedUser.getRole();
    }

    /**
     * Czyści sesje.
     */
    public void clearCurrentSession() {
        loggedUser = null;
    }
}
