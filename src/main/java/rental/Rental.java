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
 * Created by Tobiasz Rumian on 25.04.2017.
 */
public class Rental implements Serializable {
    private Map<String, User> users = new HashMap<>();
    {
        users.put("admin",new User("admin",Role.ADMIN,"admin"));
    }
    private Map<String, Product> products = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();
    private CurrentSession session = CurrentSession.getInstance();

    public void addUser(String nick, Role role, String password) throws IllegalArgumentException {
        if (session == null || session.getLoggedUserRole() != Role.ADMIN)
            throw new IllegalArgumentException("Nie masz wystarczających praw by wykonać tę akcję!");
        if (users.containsKey(nick))
            throw new IllegalArgumentException("Użytkownik o podanym nick'u już istnieje!");
        if((nick==null||nick.equals(""))||(password==null||password.equals("")))
            throw new IllegalArgumentException("Błędne dane!");
        users.put(nick, new User(nick, role, password));
    }

    public Map<String, User> showUsers() {
        return users;
    }

    public void addProduct(String name, BigDecimal price) throws IllegalArgumentException {
        if (session == null || session.getLoggedUserRole() != Role.ADMIN)
            throw new IllegalArgumentException("Nie masz wystarczających praw by wykonać tę akcję!");
        if (products.containsKey(name))
            throw new IllegalArgumentException("Produkt o podanej nazwie już istnieje!");
        products.put(name, new Product(name, price));
    }

    public Map<String,Product> showProducts(){
        return products;
    }

    public void rent(User user, Product product, int forHowMannyDays) {
        Transaction newTransaction = new Transaction(product, user, forHowMannyDays);
        product.setAvailable(false);
        addTransaction(newTransaction);
    }

    public User getUser(String nick){
        return users.get(nick);
    }


    private void addTransaction(Transaction transaction) {
        history.add(transaction);
    }

    public void deleteUser(String nick) {
        users.remove(nick);
    }

    public void deleteProduct(String name) {
        products.remove(name);
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public boolean checkCredentials(String login, String password){
        return users.containsKey(login) && users.get(login).getPassword().equals(password);
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
