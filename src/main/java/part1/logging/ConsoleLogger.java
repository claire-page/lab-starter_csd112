package part1.logging;

import java.time.Instant;

public class ConsoleLogger implements Logger{

    /**
     * Prints an error message to the console, containing the actual error info message,
     * the level of the error that occured, and the current time.
     * @param message the message to be logged
     * @param level level of the error to be logged (info, warning, error)
     */
    public void log(String message, LogLevel level) {
        System.out.println(formatMsg(message, level));
    }

}
