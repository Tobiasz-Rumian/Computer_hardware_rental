package rental;

import view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
public class Run {
    private Rental rental;
    private View view;

    private Run(){
        try{
            loadRentalFromFile();
        }catch (Exception e){
            rental=new Rental();
        }
        view=new View(this);

    }

    public Rental getRental() {
        return rental;
    }

    public void saveRentalToFile() throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Rental.rental"));
        out.writeObject(rental);
        out.close();
    }

    private void loadRentalFromFile() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Rental.rental"));
        rental = (Rental) in.readObject();
        in.close();
    }
    public static void main(String[] args) {
        new Run();
    }
}
