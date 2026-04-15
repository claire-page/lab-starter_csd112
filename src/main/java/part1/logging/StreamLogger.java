package part1.logging;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamLogger implements Logger{

    public OutputStream out;

   public StreamLogger(OutputStream outputStream){
    this.out = outputStream;
   }

    /**
     * Writes error data (message String, LogLevel, and current time)
     * to output stream provided in constructor.
     * @param message error info message to be logged
     * @param level of error to be logged (info, warning, error)
     */
    @Override
    public void log(String message, LogLevel level) {

        try { //writing to destination, added newline at the end of each message for consistency.
            this.out.write(((formatMsg(message, level))+"\n").getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
