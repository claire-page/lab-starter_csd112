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
    Position botLeft = new Position(Row.Bottom, Col.Right);
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


        Board blockable6 = new Board("-OX" +
                                         "-O-" +
                                         "---");
        assertEquals( botMid, Xomi.getNextMove(blockable6), "Omola(X) should have chosen to block the bottom-middle cell but did not.");


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
                                        "..." +
                                       "..O");
        assertEquals(midMid,Xomi.getNextMove(blockable9), "Omola() Should have chosen to block at middle middle");
    }

    @Test
    public void testOmolaPrefersWinsOverBlocks() {

    Board winorblock1 = new Board("OX-" +
                                     "O--" +
                                      "-X-");
    Position pickWin1 = new Position(Row.Middle, Col.Middle);
    assertEquals(pickWin1, Xomi.getNextMove(winorblock1), "Omola(X) should have chosen the instant win at middle middle but blocked instead.");

    Board winorblock2 = new Board("XOX" +
                                     "XO-" +
                                    "---");
    Position pickWin2 = new Position(Row.Bottom, Col.Middle);
    assertEquals(pickWin2, Oomi.getNextMove(winorblock2), "Omola(O) should have chosen the instant win at middle middle, but blocked instead.");

    Board winorblock3 = new Board("-XX" +
                                     "-OO" +
                                     "---");
    Position pickWin3 = new Position(Row.Top, Col.Left);
    assertEquals(pickWin3, Xomi.getNextMove(winorblock3), "Omola(X) should have chosen the instant win at middle left but blocked instead.");


    Board winorblock4 = new Board("XXO" +
                                     "--O" +
                                     "-X-");
    Position pickWin4 = new Position(Row.Bottom, Col.Right);
    assertEquals(pickWin4, Oomi.getNextMove(winorblock4), "Omola(O) should have chosen the instant win at bottom right but blocked instead.");
}

}