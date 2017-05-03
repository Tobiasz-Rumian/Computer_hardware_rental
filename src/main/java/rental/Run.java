package rental;

import session.CurrentSession;
import view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Klasa uruchomienia aplikacji.
 * @author Tobiasz Rumian
 */
public class Run {
    private Rental rental;

    private Run() {
        try {
            loadRentalFromFile();
        } catch (Exception e) {
            rental = new Rental();
        }
        new View("Wypożyczalnia sprzętu komputerowego", rental, this);
    }

    /**
     * Zapisuje obiekt wypożyczalni do pliku.
     * @throws Exception Zwraca wyjątek gdy plik jest w użyciu.
     */
    public void saveRentalToFile() throws Exception {
        CurrentSession.getInstance().clearCurrentSession();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Rental.rental"));
        out.writeObject(rental);
        out.close();
    }
    /**
     * Wczytuje obiekt wypożyczalni z pliku.
     * @throws Exception Zwraca wyjątek gdy plik nie istnieje.
     */
    private void loadRentalFromFile() throws Exception {
        CurrentSession.getInstance().clearCurrentSession();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Rental.rental"));
        rental = (Rental) in.readObject();
        in.close();
    }

    public static void main(String[] args) {
        new Run();
    }
}
