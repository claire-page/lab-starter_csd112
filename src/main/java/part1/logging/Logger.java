package part1.logging;

import java.time.Instant;

public interface Logger {

    public abstract void log (String message, LogLevel loglvl);

    public default String formatMsg(String message, LogLevel loglvl){
    return(Instant.now().toString() + " [" + loglvl + "] " + message);
    }

}
