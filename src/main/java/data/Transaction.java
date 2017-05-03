package data;

import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
@Data
public class Transaction implements Serializable {
    private Product product;
    private User user;
    private Calendar dateOfRental;
    private int forHowMannyDays;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Transaction(Product product, User user, int forHowMannyDays) {
        this.product = product;
        this.user = user;
        this.dateOfRental = Calendar.getInstance();
        this.forHowMannyDays = forHowMannyDays;
    }

    public Calendar getDateOfReturn() {
        Calendar x = dateOfRental;
        x.add(Calendar.DATE, forHowMannyDays);
        return x;
    }

    public String toString() {
        return product.toString() + "\t" + user.toString() + "\t" + dateOfRentalToString() + "\t" + dateOfReturnToString();
    }

    public String dateOfRentalToString() {
        return df.format(dateOfRental.getTime());
    }

    public String dateOfReturnToString() {
        return df.format(getDateOfReturn().getTime());
    }
}
