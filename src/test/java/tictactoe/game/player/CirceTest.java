package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;
class CirceTest {
    //need to test it always picks first available.
    Circe XCirce = new Circe(Token.X);
    Circe OCirce = new Circe(Token.O);

    Board empty = new Board();


    @Test
    public void circePicksExpectedMove(){

        Position midMid = new Position(Row.Middle, Col.Middle);
        Position topMid = new Position(Row.Top, Col.Middle);
        Position topRight = new Position(Row.Top, Col.Right);
        Position midRight = new Position(Row.Middle, Col.Right);
        Position botRight = new Position(Row.Bottom, Col.Right);
        Position botMid = new Position(Row.Bottom, Col.Middle);
        Position botLeft = new Position(Row.Bottom, Col.Right);
        Position midLeft = new Position(Row.Middle, Col.Left);
        Position topLeft = new Position(Row.Top, Col.Left);

        assertEquals(midMid, XCirce.getNextMove(empty));

        Board midfree1 = new Board("XOX----XX");
        assertEquals(midMid, OCirce.getNextMove(midfree1), "Circe should have picked the middle cell.");

        Board topRightNext = new Board ("XO--O----");
        assertEquals(topRight, XCirce.getNextMove(topRightNext), "Circe should have picked the top left cell");

        Board midRightNext = new Board ("-OXOX----");
        assertEquals(midRight, OCirce.getNextMove(midRightNext), "Circe should have picked the middle right cell");

        Board botRightNext = new Board ("XOXOOX---");
        assertEquals(botRight, XCirce.getNextMove(botRightNext), "Circe should have picked the bottom right cell");

        Board botMidNext = new Board ("-OXOXX--X");
        assertEquals(botMid, OCirce.getNextMove(botMidNext), "Circe should have picked the bottom middle cell");

        Board botLeftNext = new Board ("-OXOXOX--");
        assertEquals(botLeft, XCirce.getNextMove(botLeftNext), "Circe should have picked the bottom left cell");

        Board midLeftNext = new Board ("-OX-XOXOX");
        assertEquals(midLeft, OCirce.getNextMove(midLeftNext), "Circe should have picked the middle left cell");

        Board topLeftNext = new Board ("-OXOXOXOX");
        assertEquals(topLeft, XCirce.getNextMove(topLeftNext), "Circe should have picked the top left cell");

        //these are just some random ones. may be implausible but thought I'd test.

        Board random1 = new Board("-XOXO-XXX");
        assertEquals(midRight, XCirce.getNextMove(random1), "Circe should have picked the mid right cell");

        Board random2 = new Board("-X-OOOXX-");
        assertEquals(topRight, XCirce.getNextMove(random2), "Circe should have picked the top right cell");

        Board random3 = new Board("X.X.OO..O");
        assertEquals(topMid, XCirce.getNextMove(random3), "Circe should have picked the top middle cell");



    }


}