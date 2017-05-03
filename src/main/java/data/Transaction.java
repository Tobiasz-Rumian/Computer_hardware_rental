package data;

import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Klasa bazodanowa przechowująca dane o tranzakcji.
 * @author Tobiasz Rumian
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

    /**
     * Zwraca przewidywaną datę zwrotu produktu.
     * @return Przewidywana data zwrotu produktu.
     */
    public Calendar getDateOfReturn() {
        Calendar x = dateOfRental;
        x.add(Calendar.DATE, forHowMannyDays);
        return x;
    }

    public String toString() {
        return product.toString() + "\t" + user.toString() + "\t" + dateOfRentalToString() + "\t" + dateOfReturnToString();
    }

    /**
     * Zwraca datę wypożyczenia jako sformatowany tekst.
     * @return Data wypożyczenia jako tekst.
     */
    public String dateOfRentalToString() {
        return df.format(dateOfRental.getTime());
    }
    /**
     * Zwraca datę przewidywanego zwrotu jako sformatowany tekst.
     * @return Data przewidywanego zwrotu jako tekst.
     */
    public String dateOfReturnToString() {
        return df.format(getDateOfReturn().getTime());
    }
}
