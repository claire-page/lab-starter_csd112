package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Omola extends Player{
 //maybe 2 for loops would have been better...
    public Omola(Token token) {
        super("Omola", token);
    }
    /**
     *
     * @param board
     * @return a Position determined by the One-Move-LookAhead algorithm.
     */
    @Override
    public Position getNextMove(Board board) {

        Board copyBoard = new Board(board);
        ArrayList<Position> blockingmoves = new ArrayList<>();

        for (Position p: copyBoard.getEmptyCells()) {

            copyBoard.place(p, this.token);
            var winner = copyBoard.getWinner();

            if (winner.equals(Optional.of(this.token))) {
                return (p); //return immediately, a win is a win
            }
            copyBoard.place(p, null); //cleanup to keep going..
        }

        for (Position p: copyBoard.getEmptyCells()) {

            copyBoard.place(p, this.opponentToken());
            var winner2 = copyBoard.getWinner();

            if (winner2.equals(Optional.of(this.opponentToken()))) {
               return (p);
            }
            copyBoard.place(p, null); //cleanup
    }

        //should only get here if no wins or blocks.
            Random rand = new Random();
            int randomnumber = rand.nextInt(board.getEmptyCells().size());
            return (board.getEmptyCells().get(randomnumber)); //random guaranteed empty cell.
}
}
