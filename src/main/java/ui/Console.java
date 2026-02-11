package ui;

import java.util.ArrayList;
import java.util.List;

// This is an example of one of those "namespace" classes that just holds static methods
public class Console {

    // Prevent instantiation (no instances of this class should ever be created)
    // because it is just a holder for static methods
    private Console() {} //unusable.

    // The first two methods here are simple wrappers around IO methods
    // to make it so that all console interaction goes through this class
    // instead of a mix of IO and Console classes

    /**
     * Display a message to the user
     * @param message The message to display
     */
    public static void println(String message) {
        IO.println(message);
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        return IO.readln(promptMessage);
    }

    /**
     * Prompt the user for an integer input using the given promptMessage.
     * Keeps prompting until a valid integer is entered.
     * @param promptMessage The message to prompt the user with
     * @return The user's response as an integer
     */
    public static int promptForInt(String promptMessage) {
        while ( true ) {
            var input = prompt(promptMessage);
            try {
                return Integer.parseInt(input);
            } catch ( NumberFormatException e ) {
                println("Please enter a valid integer.");
            }
        }
    }

    /**
     * Prompt the user for n inputs using the given promptMessage.
     * Keeps prompting until n valid inputs are entered.
     * @param promptMessage The message to prompt the user with
     * @param n The number of inputs to collect
     * @return A list of the user's responses
     */
    public static List<String> promptForNInputs(String promptMessage, int n) {
        var inputs = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            inputs.add(prompt("%s (%d of %d): ".formatted(promptMessage, i + 1, n)));
        }
        return inputs;
    }

    /**
     * Prompt the user to select one of the given options.
     * Keeps prompting until a valid option is entered.
     * The comparison is NOT case-sensitive.
     * @param promptMessage The message to prompt the user with
     * @param options The valid options to choose from
     * @return The user's selected option
     */
    public static String promptForOption(String promptMessage, String[] options) {
        while ( true ) {
            var input = prompt(promptMessage + " (" + String.join("/", options) + "): ");
            for ( var option : options ) {
                if ( input.equalsIgnoreCase(option) ) {
                    return option;
                }
            }
            println("Please enter one of the valid options: " + String.join(", ", options));
        }
    }
}
