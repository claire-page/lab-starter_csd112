package part1.logging;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MultiLogger extends ConsoleLogger{
    public List<ConsoleLogger> loggers;

    public MultiLogger(List<ConsoleLogger> consoleLoggers) {
        super();
        this.loggers = consoleLoggers;
    }


    /** Logs the same error with every Logger provided to the constructor
     * @param msg error message to be logged
     * @param logLevel level of error to be logged
     */
    @Override
    public void log (String msg, LogLevel logLevel){
        this.loggers.forEach(l-> l.log(msg, logLevel));
    }
}
