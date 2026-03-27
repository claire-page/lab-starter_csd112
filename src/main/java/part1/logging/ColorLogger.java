package part1.logging;
import com.diogonunes.jcolor.*;

import javax.swing.*;
import java.awt.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ColorLogger extends ConsoleLogger{

    @Override
    public String formatMsg(String msg, LogLevel logLevel){

        Attribute msgColor = switch(logLevel){
            case INFO -> Attribute.BLUE_TEXT();
            case WARNING -> Attribute.BRIGHT_YELLOW_TEXT();
            case ERROR -> Attribute.RED_TEXT();
        };
        return(colorize(super.formatMsg(msg, logLevel), msgColor));
    }
}
