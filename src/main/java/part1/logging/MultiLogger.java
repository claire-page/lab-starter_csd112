package part1.logging;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MultiLogger implements Logger {
    public List<ConsoleLogger> loggers;

    public MultiLogger(List<ConsoleLogger> consoleLoggers) {
        this.loggers = consoleLoggers;
    }


    /** Logs the same error with every Logger provided to the constructor
     * @param msg info message of error to be logged
     * @param logLevel level of error to be logged (info, warning, error)
     */
    @Override
    public void log (String msg, LogLevel logLevel){
        this.loggers.forEach(l-> l.log(msg, logLevel));
    }
}
