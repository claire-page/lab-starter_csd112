package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

class OmolaTest {
    Omola Xomi = new Omola(Token.X);
    Omola Oomi = new Omola(Token.O);

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
    public void testOmolaBlocksRows(){

        Board blockable1 = new Board("X--" +
                                        "O-O" +
                                        "---");
        assertEquals(midMid,Xomi.getNextMove(blockable1),"Omola(X) Should have chosen to block at middle middle");


        Board blockable_3 = new Board("O.O" +
                                         "..." +
                                         "X..");

        assertEquals(topMid, Xomi.getNextMove(blockable_3),"Omola(X) should have chosen to block at top-middle");


        Board blockable_4 = new Board("..." +
                                         ".X-" +
                                         "O.O");
        assertEquals(botMid, (Oomi.getNextMove(blockable_4)), "Omola (X) should have chosen to block at bottom-middle cell");}


    @Test
            public void testOmolaBlocksCols(){

        //col blocks
        Board blockable_5 = new Board("X--" +
                                         ".O." +
                                         "X--");
        assertEquals(midLeft, Oomi.getNextMove(blockable_5), "Omola (O) should have chosen to block at middle-left cell ");


        Board blockable6 = new Board( "-XO" +
                                         "---" +
                                         "X-O");
        assertEquals(midRight, Xomi.getNextMove(blockable6), "Omola(X) should have chosen to block the middle-right cell but did not.");


        Board blockable7 = new Board("O--" +
                                        "--X" +
                                         "-OX");
        assertEquals(topRight, Oomi.getNextMove(blockable7), "Omola(O) should have chosen to block the top-right cell but did not.");

    }



    @Test
            public void testOmolaBlocksDiagonals(){

        Board blockable8 = new Board("X.O" +
                                         "..." +
                                         ".OX");
        assertEquals(midMid,Oomi.getNextMove(blockable8), "Omola(O) Should have chosen to block at middle middle");

        Board blockable9 = new Board("O.X" +
                                        ".O." +
                                       "...");
        assertEquals(botRight,Xomi.getNextMove(blockable9), "Omola(X) Should have chosen to block at bottom right");

        Board blockable10 = new Board("O.X" +
                                          ".X." +
                                          "...");
        assertEquals(botLeft,Oomi.getNextMove(blockable10), "Omola(O) Should have chosen to block at bottom left");

        Board blockable11 = new Board("..O" +
                                         ".O." +
                                         ".XO" );
        assertEquals(topLeft,Oomi.getNextMove(blockable11), "Omola(X) Should have chosen to block at top left");

    }

    @Test
    public void testOmolaPrefersWinsOverBlocks() {

    Board winorblock1 = new Board("OX-" +
                                     "O--" +
                                      "-X-");
        var choice1 = Xomi.getNextMove(winorblock1);
        assertEquals(midMid, choice1, "Omola(X) should have chosen the instant win at middle middle.");
        assertFalse(midRight.equals(choice1), "Omola(X) should NOT have chosen to block at bottom left");


        Board winorblock2 = new Board("XOX" +
                                         "XO-" +
                                         "---");
        var choice2 = Oomi.getNextMove(winorblock2);
        assertEquals(botMid,choice2, "Omola(O) should have chosen the instant win at middle middle.");
        assertFalse(midRight.equals(choice2), "Omola(O) should NOT have chosen to block at middle left");

    Board winorblock3 = new Board("-XX" +
                                     "-OO" +
                                     "---");
    var choice3 = Xomi.getNextMove(winorblock3);
    assertEquals(topLeft, choice3,"Omola(X) should have chosen the instant win at top left.");
    assertFalse(midLeft.equals(choice3), "Omola(X) should NOT have chosen to block at middle left");

    Board winorblock4 = new Board("XXO" +
                                     "--O" +
                                     "-X-");
    var choice4 = Oomi.getNextMove(winorblock4);
    assertEquals(botRight, choice4,"Omola(O) should have chosen the instant win at bottom right.");
    assertFalse(midMid.equals(choice3), "Omola(O) should NOT have chosen to block at middle left");

    }

}