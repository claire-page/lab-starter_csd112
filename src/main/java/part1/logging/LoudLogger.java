package part1.logging;

public class LoudLogger extends ConsoleLogger{

    @Override
    public void log(String message, LogLevel level) {
        super.log(message.toUpperCase().replace(".", "!!!!!"), level);

    }
}
