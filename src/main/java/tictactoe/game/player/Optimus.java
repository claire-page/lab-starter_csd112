package tictactoe.game.player;

import tictactoe.game.*;

import java.text.ParseException;
import java.util.Optional;
import java.util.Random;

//TODO: try using ternary whateveritscalled and simpler conditional statements??

public class Optimus extends Player {

    public Optimus(Token token){
        super("Optimus", token);
    }

    public record Retval(int score, Position position, int depth){} //adding depth :p

    /**
     *
     * @param b a Board object
     * @param t a Token object, may not necessarily belong to the player.
     * @param depth integer representing how many levels of recursion the function has achieved
     * at a given point. this should be zero for a first call.
     * @return
     */
    public Retval minMax (Board b, Token t, int depth) {
        int currentdepth = depth;
       Token opp = t.opp();
       //instead of x and o used "my token" and "other token",
        // optimus's token is always the 'maximizer'.
        //.....base case first...are we there yet(win or draw)?
        var res = b.getWinner();

        if (res.isPresent()) {

            if (res.equals(Optional.of(this.token))) {
                return(new Retval(1, null, currentdepth));
            }
            else{
                return(new Retval(-1, null, currentdepth));
            }

        }
        if(b.isFull()){
            return(new Retval(0, null, currentdepth));
        }

        else {
            Retval top;
            if (t.equals(this.token)){ //if playing as "us"

                Retval bestcase1 = new Retval(-1, null, 99);

                for (Position p: b.getEmptyCells()) {
                    Board copycurrent = new Board(b);
                    copycurrent.place(p, t);

                    var val = minMax(copycurrent, opp, currentdepth+1);
                    //found a better or equal score!
                        if ((val.score == bestcase1.score && val.depth < bestcase1.depth )|| (val.score > bestcase1.score)) { //if new guy has same score but better depth...
                            bestcase1 = new Retval(val.score, p, val.depth);
                        }
                      //otherwise keep going!
                    copycurrent.place(p, null); //cleaning up.
                }
                top = bestcase1;
            }
            else{
                Retval bestcase2 = new Retval(1, null,  99);
                    for (Position p: b.getEmptyCells()) {
                        Board copycurrent2 = new Board(b);
                        copycurrent2.place(p, t);

                        var val = minMax(copycurrent2, t.opp(), currentdepth + 1);
                        if ((val.score == bestcase2.score && val.depth < bestcase2.depth)||(val.score < bestcase2.score)){ //equal score + better depth OR just better score.
                            bestcase2 = new Retval(val.score, p, val.depth);
                        }
                        copycurrent2.place(p, null); //cleaning up.
                    }
                top = (bestcase2);
            }
            return(top);
        }
    }

    /**
     *
     * @param board
     * @return Optimal move, either a random corner (for a first ), or a move determined by the minmax function.
     */
    @Override
    public Position getNextMove(Board board) {

        if (board.isEmpty()) {
            //corners are the best choice for an empty board according to the tictactoe experts out there.
                 Position [] cornerPositions = {new Position(Row.Top, Col.Left),
                                                new Position(Row.Top, Col.Right),
                                                new Position(Row.Bottom, Col.Left),
                                                new Position(Row.Bottom, Col.Right)};
            Random rand = new Random();
            return (cornerPositions[rand.nextInt(4 )]);
        }
        else{
            return (minMax(board, this.token, 0).position); }

    }

    }

