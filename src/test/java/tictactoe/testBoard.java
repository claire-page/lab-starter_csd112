package tictactoe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import tictactoe.game.*;
import tictactoe.game.Position;

import java.text.ParseException;

import static java.lang.invoke.MethodHandles.throwException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class testBoard {


    @Test
    public void testisEmpty(){

        Board board = new Board(); //empty board

        Position filledXposition = new Position(Row.Bottom, Col.Left);
        Position filledOPosition = new Position(Row.Bottom, Col.Left);
        Position blankposition = new Position(Row.Bottom, Col.Middle);

        Token tokenX = Token.X;
        Token tokenO = Token.O;

        board.place(filledXposition, tokenX);

        assertTrue(!board.isEmptyAt(filledXposition), "Bottom left should not be empty when X token is placed there.");
        assertTrue(board.isEmptyAt(blankposition), "Bottom middle should be empty but is not.");

        board.place(filledOPosition,tokenO);

        assertTrue(!board.isEmptyAt(filledOPosition), "Bottom left should not be empty when O is placed there");
    }


    @Test
    public void testIsFull(){

        //tests full board
        Board fullboard = new Board("XOXOOXOXO");
        assertTrue(fullboard.isFull());
        //and empty board
        Board emptyboard = new Board ("---------");
        assertTrue(!emptyboard.isFull());

    }

    @Test
    public void testisWinner(){
        Board board = new Board(---------);
    }



}
