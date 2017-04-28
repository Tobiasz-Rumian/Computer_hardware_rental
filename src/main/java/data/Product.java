package data;

import lombok.Data;

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
}
