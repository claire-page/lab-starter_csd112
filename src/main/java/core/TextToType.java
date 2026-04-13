package core;

import java.io.IOException;
import java.nio.file.*;
import java.util.Random;

public class TextToType {

    private static final String [] options = {("src/main/java/files/shelley.txt"), ("src/main/java/files/bronte.txt"), ("src/main/java/files/NGGUU.txt"), ("src/main/java/files/rodney.txt")};

    public static String getRandomtxt(){
        Random rand = new Random();
        int random = rand.nextInt(4);
        var chosen = options[random];

        try {
            return(Files.readString(Paths.get(chosen))+ " ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
