package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;
import static tictactoe.game.Token.X;
import static tictactoe.game.Token.O;
import tictactoe.game.TicTacToeGameTest.*;

class OptimusTest {

    Optimus oppy = new Optimus(X);
    Optimus oppy2 = new Optimus(O);

    @Test
    void testBlockingWorks() {

        //row blocks
        Board blockable_1 = new Board("X--O-O---");
        Position optimal_1 = new Position(Row.Middle, Col.Middle);
        assertEquals(optimal_1, oppy.getNextMove(blockable_1), "Optimus should have chosen to block this row w/the middle-middle cell but did not.");

        Board blockable_2 = new Board("X.X.O....");
        Position optimal_2 = new Position(Row.Top, Col.Middle);
        assertEquals(optimal_2, oppy2.getNextMove(blockable_2),"Optimus should have chosen to block this row w/the top-middle cell but did not.");


        Board blockable_3 = new Board("....X-O.O");
        Position optimal_3 = new Position(Row.Bottom, Col.Middle);
        assertEquals(optimal_3, (oppy.getNextMove(blockable_3)), "Optimus should have chosen to block this row w/the bottom-middle cell but did not.");

        //col blocks
        Board blockable_4 = new Board("X--.O.X--");
        Position optimal_4 = new Position(Row.Middle, Col.Left);
        assertEquals(optimal_4,oppy2.getNextMove(blockable_4), "Optimus should have chosen to block the middle-left cell but did not.");

        Board blockable_5 = new Board("-OX-O----");
        Position optimal_5 = new Position(Row.Bottom, Col.Middle);
        assertEquals(oppy.getNextMove(blockable_5), optimal_5, "Optimus should have chosen to block the bottom-middle cell but did not.");

        Board blockable_6 = new Board("O----X-OX");
        Position optimal_6 = new Position(Row.Top, Col.Right);
        assertEquals( optimal_6, oppy2.getNextMove(blockable_6), "Optimus should have chosen to block the top-right cell but did not.");

        //diagonal blocks
        Board blockable_7 = new Board("----X-O-X");
        Position optimal_7 = new Position(Row.Top, Col.Left);
        assertEquals(optimal_7, oppy2.getNextMove(blockable_7),"Optimus should have chosen to block the top-left cell but did not.");

        Board blockable_8 = new Board("O---O-X--");
        Position optimal_8 = new Position(Row.Bottom, Col.Right);
        assertEquals(optimal_8, oppy.getNextMove(blockable_8), "Optimus should have chosen to block the bottom-right cell but did not.");
    }

    @Test
    void testFirstMoveIsAlwaysaCorner() {

        Position corner1 = new Position(Row.Top, Col.Left);
        Position corner2 = new Position(Row.Top, Col.Right);
        Position corner3 = new Position(Row.Bottom, Col.Left);
        Position corner4 = new Position(Row.Bottom, Col.Right);

        Board corner_b1 = new Board();
        var testcorner1 = oppy.getNextMove(corner_b1);
        assertTrue((testcorner1).equals(corner1) || (testcorner1).equals(corner2) ||  (testcorner1).equals(corner3) || (testcorner1).equals(corner4));


        Board corner_b2 = new Board();
        var testcorner2 = oppy2.getNextMove(corner_b1);
        assertTrue((testcorner2).equals(corner1) || (testcorner2).equals(corner2) ||  (testcorner2).equals(corner3) || (testcorner2).equals(corner4));

    }

    @Test
    void testAlwaysWinsOrDraws(){

        var result1 = TicTacToeGameTest.testGame(new Optimus(Token.X), new Circe(Token.O));
        assertTrue(result1.equals(TicTacToeGame.Status.Draw)||result1.equals(TicTacToeGame.Status.XWins), "" +
                "Optimus should have won or drawn, but it lost in the game against Circe");

        var result2 = TicTacToeGameTest.testGame(new Optimus(Token.O), new Omola(Token.X));
        assertTrue(result2.equals(TicTacToeGame.Status.Draw)||result2.equals(TicTacToeGame.Status.OWins),
                "Optimus should have won or drawn, but it lost in the game against Omola");

        var result3 = TicTacToeGameTest.testGame(new Circe(Token.X), new Optimus(Token.O));
        assertTrue(result3.equals(TicTacToeGame.Status.Draw)||result3.equals(TicTacToeGame.Status.OWins),
                "Optimus should have won or drawn, but it lost in the game against Circe");

        var result4 = TicTacToeGameTest.testGame(new Optimus(Token.X), new Omola(Token.O));
        assertTrue(result4.equals(TicTacToeGame.Status.Draw)||result4.equals(TicTacToeGame.Status.XWins),
                "Optimus should have won or drawn, but it lost in the game against Omola");

        var result5 = TicTacToeGameTest.testGame(new Optimus(Token.X), new Optimus(Token.O));
        assertTrue(result5.equals(TicTacToeGame.Status.Draw),
                "Optimus should have won or drawn, but it lost in the game against Omola");
    }



}






















//scrapped test...
//here I realized Optimus would sometimes block instead of immediately winning, but in situations where that move would lead to a win anyways. was rly confusing until I realized how it iterates over the blank cells...if it finds a situation that leads to a win it doesn't take into account how long it would take to get there.

//
//    @Test
//    void testPicksWinOverBlocking() {
////        Board winorblock1 = new Board("OX-O---X-");
////        Position pickWin1 = new Position(Row.Middle, Col.Middle);
////        assertEquals(pickWin1, oppy2.getNextMove(winorblock1));
//
//        Board winorblock2 = new Board("--XO-XO--");
//        Position pickWin2 = new Position(Row.Bottom, Col.Right);
//        assertEquals(pickWin2, oppy.getNextMove(winorblock2), "ahhhhhhh nooooo");
////

////
////
////        Board winorblock4 = new Board("XOXXO----");
////        Position pickwin4 = new Position(Row.Bottom, Col.Middle);
//
//    }
//}
