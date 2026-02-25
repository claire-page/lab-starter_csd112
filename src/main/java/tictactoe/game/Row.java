package tictactoe.game;

import java.text.ParseException;

/**
 * Represents the row position of a tictactoe board location
 */
public enum Row {
    Top, Middle, Bottom;

    /**
     * Converts a string representation of a TicTacToe row into an actual Row value.
     * Can be a numeric (1, 2, or 3) or text string (top, centre, bottom).
     * Common abbreviations and alternate spellings are accepted
     * (e.g. t, tp, centre, center, mid, m, md, etc.)
     * This function is NOT case-sensitive and ignores leading and trailing whitespace.
     * @param str A string representation of a row position
     * @return The row position corresponding to the given string representation
     * @throws ParseException if the given string is not a valid representation for a row position
     */
    public static Row parse(String str) throws ParseException{
        return switch (str.toLowerCase().trim()) {
            case "1", "t", "tp", "top" -> Top;
            case "2", "m", "c", "mid", "middle", "center", "centre" -> Middle;
            case "3", "b", "bt", "bot", "bottom" -> Bottom;
            default -> throw new ParseException("Invalid row: " + str, 0);
        };
    }
}
