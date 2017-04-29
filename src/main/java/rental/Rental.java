package rental;

import data.Transaction;
import data.Product;
import data.User;
import enums.Role;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
public class Rental implements Serializable{
    private Map<String,User> users=new HashMap<>();
    private Map<String,Product> products=new HashMap<>();
    private List<Transaction> history=new ArrayList<>();

    public void addUser(String nick, Role role, String password) throws IllegalArgumentException{
        if(users.containsKey(nick)) throw new IllegalArgumentException("Użytkownik o podanym nick'u już istnieje!");
        users.put(nick,new User(nick,role,password));
    }

    public void addProduct(String name, BigDecimal price) throws IllegalArgumentException{
        if(products.containsKey(name)) throw new IllegalArgumentException("Produkt o podanej nazwie już istnieje!");
        products.put(name,new Product(name,price));
    }

    private void addTransaction(Transaction transaction){
        history.add(transaction);
    }

    public void deleteUser(String nick){
        users.remove(nick);
    }
    public void deleteProduct(String name){
        products.remove(name);
    }
    public List<Transaction> getHistory(){
        return history;
    }

}
