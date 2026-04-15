package part1.logging;

import java.time.Instant;

public interface Logger {
    /**
     *
     * @param message string info-msg of error to be logged
     * @param loglvl level of error to be logged (info, warning, error)
     */
    public abstract void log (String message, LogLevel loglvl);

    public default String formatMsg(String message, LogLevel loglvl){
    return(Instant.now().toString() + " [" + loglvl + "] " + message);
    }

}
