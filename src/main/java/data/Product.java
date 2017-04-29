package data;

import lombok.Data;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
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
