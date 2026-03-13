package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

class OmolaTest {
    Omola Xomi = new Omola(Token.X);
    Omola Oomi = new Omola(Token.O);
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
    assertEquals(pickWin3, Oomi.getNextMove(winorblock3), "Omola(X) should have chosen the instant win at bottom right but blocked instead.");


    Board winorblock4 = new Board("XXO" +
                                     "--O" +
                                     "-X-");
    Position pickWin4 = new Position(Row.Bottom, Col.Right);
    assertEquals(pickWin3, Oomi.getNextMove(winorblock4), "Omola(O) should have chosen the instant win at bottom right but blocked instead.");
}

}