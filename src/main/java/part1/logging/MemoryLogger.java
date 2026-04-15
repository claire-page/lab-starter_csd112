package part1.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryLogger implements Exportable, Logger {
    List<String> memoryList;

    public MemoryLogger(){
        this.memoryList = new ArrayList<String>();
    }

    /**
     * adds error message, loglevel, and log time to
     * a List that serves as the Logger's 'memory'.
     * @param msg error info-msg to be logged
     * @param logLevel level of error to be logged (info, warning, error)
     */

    @Override
    public void log(String msg, LogLevel logLevel){
        this.memoryList.add(formatMsg(msg, logLevel)+"\n");
    } //added newlines for clearer output to console / files.

    /** writes log data stored in the MemoryLogger's memorylist to
     * the given output stream.
     * @param out The OutputStream to write to...
     */
    @Override
    public void exportTo(OutputStream out) {
        for (String errorData: this.memoryList){
            try {
                out.write(errorData.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}