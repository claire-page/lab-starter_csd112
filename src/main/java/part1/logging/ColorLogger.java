package part1.logging;
import com.diogonunes.jcolor.*;

import javax.swing.*;
import java.awt.*;

import static com.diogonunes.jcolor.Ansi.colorize;
import static part1.logging.LogLevel.*;

public class ColorLogger extends ConsoleLogger implements Logger{

    /**
     * Returns a string in the appropriate color for the type of
     * error (blue for info, yellow for warning, red for error).
     * @param message info-msg of error to be logged.
     * @param loglvl level of error message to be logged (info, warning, error)**/

    @Override
    public void log(String message, LogLevel loglvl) {

        Attribute msgColor = switch(loglvl){
            case INFO -> Attribute.BLUE_TEXT();
            case WARNING -> Attribute.BRIGHT_YELLOW_TEXT();
            case ERROR -> Attribute.RED_TEXT();
        };
        System.out.println(colorize(formatMsg(message, loglvl), msgColor));
    }

}
