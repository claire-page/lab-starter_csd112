package tictactoe.game;


import java.text.ParseException;

/**
 * Represents the column position of a tictactoe board location
 */
public enum Col {
    Left, Middle, Right;

    /**
     * Converts a string representation of a TicTacToe column into an actual Col value.
     * Can be a numeric (1, 2, or 3) or text string (left, centre, right).
     * Common abbreviations and alternate spellings are accepted
     * (e.g. l, lt, lft, centre, center, mid, m, md, etc.)
     * This function is NOT case-sensitive and ignores leading and trailing whitespace.
     * @param str A string representation of a column position
     * @return The column position corresponding to the given string representation
     * @throws ParseException if the given string is not a valid representation for a column position
     */
    public static Col parse(String str) throws ParseException {
        return switch (str.toLowerCase().trim()) {
            case "1", "l", "left", "lft", "lt" -> Left;
            case "2", "m", "c", "mid", "middle", "center", "centre" -> Middle;
            case "3", "r", "right", "rt" -> Right;
            default -> throw new ParseException("Invalid column: " + str, 0);
        };
    }
}
