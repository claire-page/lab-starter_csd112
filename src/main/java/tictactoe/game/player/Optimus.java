package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.Optional;
import java.util.Random;

public class Optimus extends Player {

    public Optimus( Token token){
        super("Optimus", token);
    }

    public record retval(int score, Position position){}


    /**
     *
     * @param b a Board object
     * @param t a Token object, may not necessarily belong to the player.
     * @return
     */
    public retval minMax (Board b, Token t) {

       Token opp = t.opp();
       //instead of x and o used "my token" and "other token",
        //optimus's token is always the 'maximizer'.


        //base case first...are we there yet(win or draw)?
        var res = b.getWinner();
        if (res.isPresent()){

            if (res.equals(Optional.of(this.token))){ //ALWAYS FALSE.

                return(new retval(1, null));
            }
            else{

                return(new retval(-1, null));

            }

        }
        if(b.isFull()){

            return(new retval(0, null));
        }

        else {
            retval top; //if our turn
            if (t.equals(this.token)){
                retval bestcase1 = new retval(-1, null);

                for (Position p: b.getEmptyCells()) {
                    Board copycurrent = new Board(b);
                    copycurrent.place(p, t);

                    var val = minMax(copycurrent, opp);
                    if (val.score > bestcase1.score){
                        bestcase1 = new retval(val.score, p);
                    }
                }
                top = bestcase1;

            } //you only get here if you are the "minimizer" (whatever ur opponent is).
            else{
                retval bestcase2 = new retval(1, null);
                    for (Position p: b.getEmptyCells()) {
                        Board copycurrent2 = new Board(b);

                        copycurrent2.place(p, t);
                        var val = minMax(copycurrent2, opp);
                        if (val.score < bestcase2.score){
                            bestcase2 = new retval(val.score, p);
                        }
                    }
                top = (bestcase2);
            }
            return(top);

        }
    }

    /**
     *
     * @param board
     * @return Optimal move as determined by minmax function..
     */
    @Override
    public Position getNextMove(Board board) {

        if (board.isEmpty()) {

            //corners are the best choice for an empty board according to the tictactoe experts out there.

            Position[] cornerPositions ; //just declaring.
            try {
                cornerPositions = new Position[]{  Position.parse("top left"),
                                                Position.parse("top right"),
                                                Position.parse("bot left"),
                                                Position.parse("bot right") };

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Random rand = new Random();
            return(cornerPositions[rand.nextInt(4 )]);
        }
        return minMax(board, this.token).position;
    }

}

