package part1.logging;

public class LoudLogger extends ConsoleLogger implements Logger{
    /**
     * Prints to the console an error message using capital letters and exclamation points instead of periods.
     * Message includes an actual message of what occured, the current time, and the level of error.
     * @param message string info-msg of the error to be loudly logged
     * @param level of error to be loudly logged (info, warning, error)
     */
    @Override
    public void log(String message, LogLevel level) {
        super.log(message.toUpperCase().replace(".", "!!!!!"), level);

    }
}
