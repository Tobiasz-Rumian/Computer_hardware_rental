package data;

import lombok.Data;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
@Data
public class Transaction implements Serializable {
    private Product product;
    private User user;
    private Calendar dateOfRental;
    private int forHowMannyDays;

    public Transaction(Product product, User user, int forHowMannyDays) {
        this.product = product;
        this.user = user;
        this.dateOfRental = Calendar.getInstance();
        this.forHowMannyDays = forHowMannyDays;
    }

    public Calendar getDateOfReturn(){
        Calendar x = dateOfRental;
        x.add(Calendar.DATE, forHowMannyDays);
        return x;
    }
    public String toString(){
        return product.toString()+"\t"+user.toString()+"\t"+dateOfRental.toString()+"\t"+getDateOfReturn().toString();
    }
}
