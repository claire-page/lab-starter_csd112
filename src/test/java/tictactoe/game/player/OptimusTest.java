package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;
import tictactoe.game.TicTacToeGameTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static tictactoe.game.Token.X;
import static tictactoe.game.Token.O;

class OptimusTest {

    Optimus Ooppy = new Optimus(X);
    Optimus Xoppy = new Optimus(O);
//wanted to be able to import and name these across all test files, unimportant but i will prob come back and do that.

    Position midMid = new Position(Row.Middle, Col.Middle);
    Position topMid = new Position(Row.Top, Col.Middle);
    Position topRight = new Position(Row.Top, Col.Right);
    Position midRight = new Position(Row.Middle, Col.Right);
    Position botRight = new Position(Row.Bottom, Col.Right);
    Position botMid = new Position(Row.Bottom, Col.Middle);
    Position botLeft = new Position(Row.Bottom, Col.Left);
    Position midLeft = new Position(Row.Middle, Col.Left);
    Position topLeft = new Position(Row.Top, Col.Left);

    @Test
    void testOptimusBlocksCols() {

        //row blocks
        Board blockable_1 = new Board(
                "X--" +
                "O-O" +
                "---");
        assertEquals(midMid, Ooppy.getNextMove(blockable_1), "Optimus (O) should have chosen to block this row w/the middle-middle cell but did not.");

        Board blockable_2 = new Board(
                "X.X" +
                ".O." +
                "...");
        assertEquals(topMid, Xoppy.getNextMove(blockable_2), "Optimus (X) should have chosen to block this row w/the top-middle cell but did not.");


        Board blockable_3 = new Board("..." +
                ".X-" +
                "O.O");
        assertEquals(botMid, (Ooppy.getNextMove(blockable_3)), "Optimus (O) should have chosen to block this row w/the bottom-middle cell but did not.");
    }

    @Test
    void testOptimusBlocksRows() {
        //col blocks
        Board blockable_4 = new Board("X--" +
                ".O." +
                "X--");
        assertEquals(midLeft, Xoppy.getNextMove(blockable_4), "Optimus (X) should have chosen to block the middle-left cell but did not.");

        Board blockable_5 = new Board("-OX" +
                "-O-" +
                "---");
        assertEquals(botMid, Ooppy.getNextMove(blockable_5), "Optimus (O) should have chosen to block the bottom-middle cell but did not.");

        Board blockable_6 = new Board("O--" +
                "--X" +
                "-OX");
        assertEquals(topRight, Xoppy.getNextMove(blockable_6), "Optimus (X) should have chosen to block the top-right cell but did not.");
    }


    @Test
    void testOptimusBlocksDiagonals() {

        //diagonal blocks
        Board blockable_7 = new Board(
                "---" +
                "-X-" +
                "O-X");
        assertEquals(topLeft, Xoppy.getNextMove(blockable_7), "Optimus () should have chosen to block the top-left cell but did not.");

        Board blockable_8 = new Board("O--" +
                "-O-" +
                "X--");
        assertEquals(botRight, Ooppy.getNextMove(blockable_8), "Optimus should have chosen to block the bottom-right cell but did not.");


        Board blockable_9 = new Board("O-X" +
                "-O-" +
                "X-X");
        assertEquals(midRight, Ooppy.getNextMove(blockable_9), "Optimus should have chosen to block the mid-right cell but did not.");
    }

    @Test
    void testFirstMoveIsAlwaysaCorner() {


        for (int i = 0; i < 99; i++) {
            Board corner_b1 = new Board();
            var testcorner1 = Ooppy.getNextMove(corner_b1);
            assertTrue((testcorner1).equals(topLeft) || (testcorner1).equals(topRight) || (testcorner1).equals(botLeft) || (testcorner1).equals(botRight), ("Optimus should always pick a corner. Expected one of bottom left, bottom right, top left, top right, but it picked %s").formatted(testcorner1));
        }

    }

    @Test
    void doesOptimusMakeOptimalMove() {
        Board b1 = new Board("OOX" +
                "X--" +
                "---");
        assertEquals(midMid, Ooppy.getNextMove(b1), "Optimus(O) should have picked the optimal move (middle, middle) but did not");

    }

    @Test
    void testOptimusAlwaysWinsOrDraws() {

        for (int i = 0; i < 99; i++) {
            var result1 = TicTacToeGameTest.testGame(new Optimus(Token.X), new Circe(Token.O));
            assertTrue(result1.equals(TicTacToeGame.Status.Draw) || result1.equals(TicTacToeGame.Status.XWins),
                    "Optimus should have won or drawn, but it lost in the game against Circe");
        }

        for (int j = 0; j < 99; j++) {
            var result2 = TicTacToeGameTest.testGame(new Optimus(Token.O), new Omola(Token.X));
            assertTrue(result2.equals(TicTacToeGame.Status.Draw) || result2.equals(TicTacToeGame.Status.OWins),
                    "Optimus should have won or drawn every game, but it lost in the game against Omola");
        }

        for (int l = 0; l < 99; l++) {
            var result4 = TicTacToeGameTest.testGame(new Optimus(Token.X), new Omola(Token.O));
            assertTrue(result4.equals(TicTacToeGame.Status.Draw) || result4.equals(TicTacToeGame.Status.XWins),
                    "Optimus should have won or drawn, but it lost in the game against Omola");
        }

        for (int m = 0; m < 99; m++) {
            var result5 = TicTacToeGameTest.testGame(new Optimus(Token.O), new Circe(Token.X));
            assertTrue(result5.equals(TicTacToeGame.Status.Draw) || result5.equals(TicTacToeGame.Status.OWins),
                    "Optimus should have won or drawn, but it lost in the game against Circe");
        }

        for (int n = 0; n < 99; n++) {
            var result5 = TicTacToeGameTest.testGame(new Optimus(Token.O), new Optimus(Token.X));
            assertTrue(result5.equals(TicTacToeGame.Status.Draw),
                    "Every Optimus v Optimus game should result in a tie.");
        }
    }

        @Test
        void testOptimusPicksWinOverBlocking() {

            Board winorblock1 = new Board("O--" +
                                             "X-X" +
                                             "-OO");

            var move1 = Xoppy.getNextMove(winorblock1);
            assertEquals(midMid, move1, "Optimus(X) should pick middle middle, as it's a winning move.");
            assertFalse(move1.equals(botLeft), "Optimus(X) should not block at bottom left!");

            Board winorblock2 = new Board("--X" +
                                             "O-X" +
                                             "O--");
            var move2 = Ooppy.getNextMove(winorblock2);
            assertEquals(botRight, move2, "Optimus(O) should pick bottom right, as it's the winning move, but it blocked instead.");
            assertFalse(move2.equals(topLeft), "Optimus(O) should not block at top left!");



            Board winorblock4 = new Board("X-O" +
                                             "XO-" +
                                             "---");
            var move3 = Xoppy.getNextMove(winorblock4);
            assertEquals(botLeft, move3, "Optimus(X) should pick bottom Left, as it's the winning move");
            assertFalse(move3.equals(botRight), "Optimus(O) should not block at bottom right!");

        }
    }
