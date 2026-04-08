package core;

import java.math.BigDecimal;
import java.util.Date;
//going to send this to the database...
public record RunData(Double time, int keyStrokes, int faults ,int backTracked){
};

