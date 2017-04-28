package rental;

import data.HistoryRecord;
import data.Product;
import data.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
public class Rental implements Serializable{
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<HistoryRecord> history;


}
