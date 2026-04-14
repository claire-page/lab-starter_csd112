package core;

import java.io.IOException;
import java.nio.file.*;
import java.util.Random;

public class TextToType {

    private static final String [] options = {("src/main/java/files/shelley.txt"),("src/main/java/files/jordan.txt"), ("src/main/java/files/text.txt"), ("src/main/java/files/bronte.txt"), ("src/main/java/files/NGGUU.txt"), ("src/main/java/files/profmartin.txt"), ("src/main/java/files/test.txt")};

    /**
     *
     * @return a String of text read from a random file.
     */
    public static String getRandomtxt(){
        Random rand = new Random();
        int random = rand.nextInt(options.length); //number of files we have.
        var chosen = options[random];

        try {
            return(Files.readString(Paths.get(chosen)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
