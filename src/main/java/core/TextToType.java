package core;

import java.io.IOException;
import java.nio.file.*;
import java.util.Random;

public class TextToType {
    private static final String [] options = {("src/main/java/io/files/shelley.txt"),("src/main/java/io/files/jordan.txt"), ("src/main/java/io/files/text.txt"), ("src/main/java/io/files/bronte.txt"), ("src/main/java/io/files/NGGUU.txt"), ("src/main/java/io/files/thx.txt"), ("src/main/java/io/files/starwars.txt")};

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
