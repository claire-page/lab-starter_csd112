package tictactoe.game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


public class testBoard {

    private static Board testboard;

    @BeforeAll
    public static void initBlankBoard(){
        testboard = new Board();
    }


    @Test
    public void testConstructor(){

        assertFalse(testboard.isFull());
        assertNotNull(testboard);
        assertNotNull(testboard.toString());

    }



    @Test
    public void testPlace (){
        try{
            Position midmid = Position.parse("Middle middle");
        testboard.place(midmid, Token.X);
        assertFalse(testboard.isEmptyAt(midmid), "Board should not be empty at the middle/middle cell.");
        }

        catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    public void testisEmpty() {

        Position filledXposition = new Position(Row.Bottom, Col.Left);
        Position filledOPosition = new Position(Row.Bottom, Col.Left);
        Position blankposition = new Position(Row.Bottom, Col.Middle);

        Token tokenX = Token.X;
        Token tokenO = Token.O;

        testboard.place(filledXposition, tokenX);

        assertFalse(testboard.isEmptyAt(filledXposition), "Bottom left should not be empty when X token is placed there.");
        assertTrue(testboard.isEmptyAt(blankposition), "Bottom middle should be empty but is not.");
        testboard.place(filledOPosition, tokenO);

        assertFalse(testboard.isEmptyAt(filledOPosition), "Bottom left should not be empty when O is placed there");
    }


    @Test
    public void testIsFull() {

        //tests full board
        Board fullboard = new Board("XOXOOXOXO");
        assertTrue(fullboard.isFull());

        Board emptyboard = new Board("---------");
        assertFalse(emptyboard.isFull());

        Board almostFull = new Board("XOXOOXOX-");
        assertFalse(almostFull.isFull());

    }

    @Test
    public void testisWinnerOnRows() {

        Board xWinTopRow = new Board("XXX\nOOX\nXO-");
        Board oWinTopRow = new Board("OOO\nX--\nOX-");

        Board xWinMidRow = new Board("XOO\nXXX\n--O");
        Board oWinMidRow = new Board("XOX\nOOO\n--O");

        Board xWinBotRow = new Board("OOX\n-OO\nXXX");
        Board oWinBotRow = new Board("OOX\nXX-\nOOO");


        assertEquals(xWinTopRow.getWinner().get(), Token.X, ("A row of Xs in the top row should have returned X as the winner, but it returned %s").formatted(xWinTopRow.getWinner().get()));

        assertEquals(oWinTopRow.getWinner().get(), Token.O, ("A row of Os in the top row should have returned O as the winner, but it returned %s").formatted(oWinTopRow.getWinner().get()));

        assertEquals(xWinMidRow.getWinner().get(), Token.X, ("A row of Xs in the middle row should have returned X as the winner, but it returned %s").formatted(xWinMidRow.getWinner().get()));

        assertEquals(oWinMidRow.getWinner().get(), Token.O, ("A row of Os in the middle row should have returned O as the winner, but it returned %s").formatted(oWinMidRow.getWinner().get()));

        assertEquals(xWinBotRow.getWinner().get(), Token.X, ("A row of Xs in the bottom row should have returned X as the winner, but it returned %s").formatted(xWinBotRow.getWinner().get()));

        assertEquals(oWinBotRow.getWinner().get(), Token.O, ("A row of Os in the bottom row should have returned O as the winner, but it returned %s").formatted(oWinBotRow.getWinner().get()));

    }

    @Test
    public void testisWinnerOnCols() {
        Board xWinLeftCol = new Board("XOX\nX-X\nX--");
        Board oWinLeftCol = new Board("OXO\nO-X\nO-X");


        Board xWinMidCol = new Board("OXX\nOX-\n-XX");
        Board oWinMidCol = new Board("OOX\nXO-\n-OX");


        Board xWinRightCol = new Board("--X\nOXX\nOOX");
        Board oWinRightCol = new Board("XXO\nXOO\n--O");

        assertEquals(xWinLeftCol.getWinner().get(), Token.X,
                ("A row of Xs in the left column should have returned X as the winner, but it returned %s").formatted(xWinLeftCol.getWinner().get()));

        assertEquals(oWinLeftCol.getWinner().get(), Token.O,
                    ("A row of Os in the left column should have returned O as the winner, but it returned %s").formatted(oWinLeftCol.getWinner().get()));


        assertEquals(xWinMidCol.getWinner().get(), Token.X,
                    ("A row of Xs in the middle column should have returned X as the winner, but it returned %s").formatted(xWinMidCol.getWinner().get()));

        assertEquals(oWinMidCol.getWinner().get(), Token.O,
                ("A row of Os in the middle column should have returned O as the winner, but it returned %s").formatted(oWinMidCol.getWinner().get()));


        assertEquals(xWinRightCol.getWinner().get(), Token.X,
                ("A row of Xs in the right column should have returned X as the winner, but it returned %s").formatted(xWinRightCol.getWinner().get()));

        assertEquals(oWinRightCol.getWinner().get(), Token.O,
                    ("A row of Os in the right column should have returned O as the winner, but it returned %s").formatted(oWinRightCol.getWinner().get()));}


@Test
    public void testIsWinnerOnDiags(){

        Board xWinLeftDiag =  new Board("X-O\nXXO\n-OX");
        Board oWinLeftDiag = new Board("OXX\nXO-\n-XO");

        Board xWinRightDiag = new Board("O-X\n-XO\nXO-");
        Board oWinRightDiag = new Board("-XO\n-OX\nOXO");



        assertEquals(oWinLeftDiag.getWinner().get(), Token.O, ("A row of Os in the left diagonal should have returned O as the winner, but it returned %s").formatted(oWinLeftDiag.getWinner().get()));

        assertEquals(xWinLeftDiag.getWinner().get(), Token.X, ("A row of Xs in the left diagonal should have returned X as the winner, but it returned %s").formatted(xWinLeftDiag.getWinner().get()));

        assertEquals(oWinRightDiag.getWinner().get(), Token.O, ("A row of Os in the right diagonal should have returned O as the winner, but it returned %s").formatted(oWinRightDiag.getWinner().get()));

        assertEquals(xWinRightDiag.getWinner().get(), Token.X, ("A row of Xs in the right diagonal should have returned X as the winner, but it returned %s").formatted(xWinRightDiag.getWinner().get()));

        }



    @Test
    public void testisWinnerReturnsNothingforNoWinnerSituations() {

        Board emptyboard = new Board("---------");
        Board fullBoardNoWin = new Board("XXO\n" + "OOX\n" + "XOX");

        assertTrue(fullBoardNoWin.getWinner().isEmpty(), ("Full board with no winner should have returned no winner but it returned %s as the Winner").formatted(fullBoardNoWin.getWinner()));
        assertTrue(emptyboard.getWinner().isEmpty(), ("Empty board should have returned no winner but it returned %s as the Winner").formatted(fullBoardNoWin.getWinner()));

    }


}