package data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
@Data
public class HistoryRecord implements Serializable {
    private String productName;
    private String user;
    private Date dateOfRental;

}
