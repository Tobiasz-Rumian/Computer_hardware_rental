package rental;

import data.Product;
import data.Transaction;
import data.User;
import enums.Role;
import session.CurrentSession;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Klasa przechowująca dane wypożyczalni.
 * Na potrzeby testów przy pierwszym uruchomieniu tworzone jest konto administratora danych logowania:
 * Login: admin
 * Hasło: admin
 * @author Tobiasz Rumian
 */
public class Rental implements Serializable {
    private Map<String, User> users = new HashMap<>();

    {
        users.put("admin", new User("admin", Role.ADMIN, "admin"));
    }

    private Map<String, Product> products = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();

    /**
     * Dodaje użytkownika do mapy.
     * @param nick Login użytkownika.
     * @param role Rola użytkownika.
     * @param password Hasło użytkownika.
     * @throws IllegalArgumentException Błędne dane wejściowe.
     */
    public void addUser(String nick, Role role, String password) throws IllegalArgumentException {
        if (CurrentSession.getInstance().getLoggedUserRole() != Role.ADMIN)
            throw new IllegalArgumentException("Nie masz wystarczających praw by wykonać tę akcję!");
        if (users.containsKey(nick))
            throw new IllegalArgumentException("Użytkownik o podanym nick'u już istnieje!");
        if ((nick == null || nick.equals("")) || (password == null || password.equals("")))
            throw new IllegalArgumentException("Błędne dane!");
        users.put(nick, new User(nick, role, password));
    }

    /**
     * Dodaje produkt do mapy.
     * @param name Nazwa produktu.
     * @param price Cena produktu.
     * @throws IllegalArgumentException Błędne dane wejściowe.
     */
    public void addProduct(String name, BigDecimal price) throws IllegalArgumentException {
        if (CurrentSession.getInstance().getLoggedUserRole() != Role.ADMIN)
            throw new IllegalArgumentException("Nie masz wystarczających praw by wykonać tę akcję!");
        if (products.containsKey(name))
            throw new IllegalArgumentException("Produkt o podanej nazwie już istnieje!");
        products.put(name, new Product(name, price));
    }

    /**
     * Wypożycza produkt.
     * @param user Użytkownik wypożyczający produkt.
     * @param product Wypożyczany produkt.
     * @param forHowMannyDays Na ile dni produkt jest wypożyczany.
     */
    public void rent(User user, Product product, int forHowMannyDays) {
        Transaction newTransaction = new Transaction(product, user, forHowMannyDays);
        product.setAvailable(false);
        addTransaction(newTransaction);
    }

    /**
     * Zwraca użytkownika o podanym loginie.
     * @param nick Login użytkownika.
     * @return Obiekt użytkownika.
     */
    public User getUser(String nick) {
        return users.get(nick);
    }

    /**
     * Dodaje tranzakcje do historii.
     * @param transaction Dodawana tranzakcja.
     */
    private void addTransaction(Transaction transaction) {
        history.add(transaction);
    }

    /**
     * Zwraca historię tranzakcji.
     * @return Lista tranzakcji.
     */
    public List<Transaction> getHistory() {
        return history;
    }

    /**
     * Sprawdza, czy użytkownik o podanym loginie i haśle istnieje
     * @param login Login użytkownika.
     * @param password Hasło użytkownika.
     * @return Zwraca true jeżeli dane użytkownika się zgadzają.
     */
    public boolean checkCredentials(String login, String password) {
        return users.containsKey(login) && users.get(login).getPassword().equals(password);
    }

    /**
     * Zwraca mapę produków.
     * @return Mapa produktów.
     */
    public Map<String, Product> getProducts() {
        return products;
    }

    /**
     * Zwraca mapę użytkowników.
     * @return Mapa użytkowników.
     */
    public Map<String, User> getUsers() {
        return users;
    }
}
