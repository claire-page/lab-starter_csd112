package tictactoe.game;

import com.diogonunes.jcolor.Attribute;

import javax.swing.text.html.HTML;

/**
 * Represents the current state of a tictactoe game, including which player's turn it is,
 * and the current state of the game board
 */
public class TicTacToeGame {

    /**
     * Represents the high-level status of the game
     */
    public enum Status { InProgress, Draw, XWins, OWins }

    /**
     * The players in the game
     */
    private final Player playerX;
    private final Player playerO;

    /**
     * The current state of the game board
     */
    private final Board board = new Board();

    private Player whoseTurn;

    /**
     * Initialize a new TicTacToe game with the given players
     * @param playerXName The X player name
     * @param playerOName The O player name
     */
    public TicTacToeGame(String playerXName, String playerOName) {
        this.playerX = new Player(playerXName, Token.X, Attribute.TEXT_COLOR(200));
        this.playerO = new Player(playerOName, Token.O, Attribute.TEXT_COLOR(25));

        this.whoseTurn = playerX;
    }

    /**
     * Get the player whose turn it is to play next
     * @return The player whose turn it is to play next
     */
    public Player whoseTurn() {
        return whoseTurn;
    }

    public Board getBoard() {
        return new Board(board);
    }

    /**
     * Check if the given position is valid for placing a token (i.e. it's empty)
     * @param pos The position to check
     * @return True if the position is valid for placing a token, false otherwise
     */
    public boolean isValidPosition(Position pos) {
        return board.isEmptyAt(pos);
    }

    /**
     * Place the current player's token at the given position, and switch turns if the game is still in progress
     * @param pos The position to place the token at
     */
    public void placeTokenAt(Position pos) {
        board.place(pos, whoseTurn.token());

        // If the game is still in progress, switch turns
        // (If there's a winner, we DON'T want to switch turns so that 'whoseTurn' still reflects the previous
        // turn-taker as the winner)
        if ( getStatus() == Status.InProgress ) {
            if ( whoseTurn == playerX ) {
                whoseTurn = playerO;
            } else {
                whoseTurn = playerX;
            }
        }
    }

    /**
     * @return The current status of the game board
     */
    public Status getStatus() {
        if (board.getWinner().isEmpty()) {    // If there's no winner...
            if (!board.isFull()) {            // ...a non-full board means the game is still in progress
                return Status.InProgress;
            } else {                          // ...while a full board means it's a draw
                return Status.Draw;
            }
        } else {
            return board.getWinner().get() == Token.X ? Status.XWins : Status.OWins;
        }
    }

}
