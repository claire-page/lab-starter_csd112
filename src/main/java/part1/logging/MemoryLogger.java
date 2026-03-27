package part1.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryLogger extends ConsoleLogger implements Exportable {
    List<String> memoryList;


    public MemoryLogger(){
        this.memoryList = new ArrayList<String>();
    }
    @Override
    public void log(String msg, LogLevel logLevel){
        this.memoryList.add(formatMsg(msg, logLevel)+"\n");

    }

    @Override
    public void exportTo(OutputStream out) {
        for (String msg: this.memoryList){
            try {
                out.write(msg.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
