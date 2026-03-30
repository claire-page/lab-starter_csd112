package part1.logging;
import com.diogonunes.jcolor.*;

import javax.swing.*;
import java.awt.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ColorLogger extends ConsoleLogger{

    /**
     * Returns a string in the appropriate color for the type of
     * error (blue for info, yellow for warning, red for error).
     * @param msg info-msg of error to be logged.
     * @param logLevel level of error message to be logged (info, warning, error)
     * @return colorized version of the correctly formatted error-msg String.
     */
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
