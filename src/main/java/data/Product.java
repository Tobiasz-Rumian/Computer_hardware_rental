package data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Klasa bazodanowa przechowująca dane produktu.
 * @author Tobiasz Rumian
 */
@Data
public class Product implements Serializable{
    private String name;
    private boolean isAvailable;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.isAvailable = true;
        this.price = price;
    }
    public String toString(){
        return name;
    }

}
