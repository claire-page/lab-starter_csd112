package core;

import java.math.BigDecimal;
import java.util.Date;
//going to send this to the database...
public record RunData(Player player, int Faults , Double time, int accuracy, Date date){
};
