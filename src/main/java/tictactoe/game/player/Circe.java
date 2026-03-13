package tictactoe.game.player;

import tictactoe.game.*;

import java.text.ParseException;
import java.util.List;

public class Circe extends Player{

    public Circe(Token token) {
        super("Circe", token); }

    public static Position[] circeMoves = {new Position(Row.Middle, Col.Middle),
            new Position(Row.Top, Col.Middle),
            new Position(Row.Top, Col.Right),
            new Position(Row.Middle, Col.Right),
            new Position(Row.Bottom, Col.Right),
            new Position(Row.Bottom, Col.Middle),
            new Position(Row.Bottom, Col.Left),
            new Position(Row.Middle, Col.Left),
            new Position(Row.Top, Col.Left)
    };

    @Override
    public Position getNextMove(Board board) {

        for (Position p : circeMoves) {
            if (board.isEmptyAt(p)) {
                return (p);
            }

        }
        return(null);
    }
}
