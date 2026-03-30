package part1.logging;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamLogger extends ConsoleLogger{

    public OutputStream dest;

   public StreamLogger(OutputStream outputStream){
    this.dest = outputStream;
   }

    /**
     * Writes error data to output stream provided in constructor.
     * @param message error message to be logged
     * @param level of error to be logged
     */
    @Override
    public void log(String message, LogLevel level) {

        try { //writing to destination, added newline at the end of each message for consistency.
            this.dest.write(((formatMsg(message, level))+"\n").getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
