package tictactoe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import tictactoe.game.*;
import tictactoe.game.Position;

import java.text.ParseException;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.empty;
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

        Board emptyboard = new Board("---------"); //how to make syre it returns nothing?
        Board fullBoardNoWin = new Board ("XXO\n" + "OOX\n" + "XOX");
        Board xWinTopRow = new Board ("XXX\nOOX\nXO-");
        Board oWinMidRow= new Board ("XOX\nOOO\n--O");
        Board xWinBotRow = new Board ("OOX\n-OO\nXXX");

        Board oWinLeftCol = new Board ("OXO\nO-X\nO-X");
        Board xWinMidCol = new Board ("OXX\nOX-\n-XX");
        Board oWinRightCol = new Board ("XXO\nXOO\n--O");

        Board oWinLeftDiag = new Board ("OXX\nXO-\n-XO");
        Board xWinRightDiag = new Board ("O-X\n-XO\nXO-");

        assertTrue(emptyboard.getWinner().equals(Optional.empty()), ("Empty board should have returned Optional.empty() but it returned %s").formatted(emptyboard.getWinner()));

        assertTrue(fullBoardNoWin.getWinner().isEmpty(),  ("Full board with no winner should have returned Optional.empty() but it returned a winner")); //i should do this with regular assertions instead of assertTrue??

        assertTrue(xWinTopRow.getWinner().get().equals( Token.X ), ("A row of Xs in the top row should have returned X as the winner, but it returned %s").formatted(xWinTopRow.getWinner().get()));

        assertTrue(oWinMidRow.getWinner().get().equals(Token.O), ("A row of Os in the middle row should have returned O as the winner, but it returned %s").formatted(oWinMidRow.getWinner().get()));

        assertTrue(xWinBotRow.getWinner().get().equals(Token.X), ("A row of Xs in the bottom row should have returned X as the winner, but it returned %s").formatted(xWinBotRow.getWinner().get()));

        assertTrue(oWinLeftCol.getWinner().get().equals(Token.O), ("A row of Os in the left column should have returned O as the winner, but it returned %s").formatted(oWinLeftCol.getWinner().get()));

        assertTrue(xWinMidCol.getWinner().get().equals(Token.X), ("A row of Xs in the middle column should have returned X as the winner, but it returned %s").formatted(xWinMidCol.getWinner().get()));

        assertTrue(oWinRightCol.getWinner().get().equals(Token.O), ("A row of Os in the right column should have returned O as the winner, but it returned %s").formatted(oWinRightCol.getWinner().get()));

        assertTrue(xWinRightDiag.getWinner().get().equals(Token.X), ("A row of Xs in the left diagonal should have returned X as the winner, but it returned %s").formatted(xWinRightDiag.getWinner().get()));

        assertTrue(oWinLeftDiag.getWinner().get().equals(Token.O), ("A row of Os in the left diagonal should have returned O as the winner, but it returned %s").formatted(oWinLeftDiag.getWinner().get()));


    }



}
