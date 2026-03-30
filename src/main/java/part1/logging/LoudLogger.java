package part1.logging;

public class LoudLogger extends ConsoleLogger{
    /**
     * Prints to the console an error message using capital letters and exclamation points instead of periods.
     * @param message of error to be loudly logged
     * @param level of error to be loudly logged
     */
    @Override
    public void log(String message, LogLevel level) {
        super.log(message.toUpperCase().replace(".", "!!!!!"), level);

    }
}
