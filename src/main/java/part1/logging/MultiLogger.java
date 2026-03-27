package part1.logging;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MultiLogger extends ConsoleLogger{
    //needs a constructor lol.
    public List<ConsoleLogger> loggers;

    public MultiLogger(List<ConsoleLogger> consoleLoggers) {
        super();
        this.loggers = consoleLoggers;
    }

//gonna try and do this the crazy functional way...
//did not read the instructions but when i did decided to pass the loggers as an instance variable.
    //which preserved the identity of the log function so I could override it.

    @Override
    public void log(String msg, LogLevel logLevel) {
        var loggerspassed = this.loggers;

        loggerspassed.forEach(logger ->{ log(msg, logLevel);});
    }

}
