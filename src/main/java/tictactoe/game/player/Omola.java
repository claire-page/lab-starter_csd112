package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Omola extends Player{

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

            copyBoard.place(p,this.token);
            var winner = copyBoard.getWinner();

            if (winner.equals(Optional.of(this.token))){
               return(p); //return immediately, a win is a win
            }
            //try same position with the enemy. somehow find it weird logically but i didn't want two for loops. seemed weird.
            copyBoard.place(p, this.opponentToken());

            var winner2 = copyBoard.getWinner();

            if (winner2.equals(Optional.of(this.opponentToken()))) {
                blockingmoves.add(p); //so stash it.
            }
            copyBoard.place(p, null); //"cleaning up"!
    }
        if (blockingmoves.isEmpty()) { //if all the positions have been tried, and there's no wins AND there's no blocks...

            Random rand = new Random();
            int randomnumber = rand.nextInt(board.getEmptyCells().size());
            return board.getEmptyCells().get(randomnumber); //random guaranteed empty cell.

        } else { //any blocking move is fine, if there's multiple you're screwed anyways.
           return(blockingmoves.get(0));
        }
    }
}
