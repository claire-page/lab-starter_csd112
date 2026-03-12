package tictactoe.game.player;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Col;
import tictactoe.game.Position;
import tictactoe.game.Row;

import static org.junit.jupiter.api.Assertions.*;
import static tictactoe.game.Token.X;
import static tictactoe.game.Token.O;

class OptimusTest {

    @Test
    void testBlockingWorks() {

        Optimus oppy = new Optimus(X);
        Optimus oppy2 = new Optimus(O);

        //row blocks
        Board blockable_1 = new Board("X--O-O---");
        Position optimal_1 = new Position(Row.Middle, Col.Middle);
        assertEquals(oppy.getNextMove(blockable_1), optimal_1, "Optimus should have chosen to block this row w/the middle-middle cell but did not.");

        Board blockable_2 = new Board("XX---O---");
        Position optimal_2 = new Position(Row.Top, Col.Middle);
        assertEquals(oppy2.getNextMove(blockable_2), optimal_2, "Optimus should have chosen to block tihs row w/the top-middle cell but did not.");


        Board blockable_3 = new Board("-----XO-O");
        Position optimal_3 = new Position(Row.Bottom, Col.Middle);
        assertEquals(oppy.getNextMove(blockable_3), optimal_3, "Optimus should have chosen to block this row w/the bottom-middle cell but did not.");

        //col blocks
        Board blockable_4 = new Board("X-----X--");
        Position optimal_4 = new Position(Row.Middle, Col.Left);
        assertEquals(oppy2.getNextMove(blockable_4), optimal_4, "Optimus should have chosen to block the middle-left cell but did not.");

        Board blockable_5 = new Board("-OX-O----");
        Position optimal_5 = new Position(Row.Bottom, Col.Middle);
        assertEquals(oppy.getNextMove(blockable_5), optimal_5, "Optimus should have chosen to block the bottom-middle cell but did not.");

        Board blockable_6 = new Board("O---X-OX");
        Position optimal_6 = new Position(Row.Top, Col.Right);
        assertEquals(oppy2.getNextMove(blockable_6), optimal_6, "Optimus should have chosen to block the top-right cell but did not.");

        //diagonal blocks
        Board blockable_7 = new Board("O-------O");
        Position optimal_7 = new Position(Row.Middle, Col.Middle);
        assertEquals(oppy.getNextMove(blockable_7), optimal_7, "Optimus should have chosen to block the middle-middle cell but did not.");

        Board blockable_8 = new Board("--XOX----");
        Position optimal_8 = new Position(Row.Bottom, Col.Left);
        assertEquals(oppy.getNextMove(blockable_8), optimal_8, "Optimus should have chosen to block the bottom-left cell but did not.");
    }

}

    @Test
    void testFirstMoveIsAlwaysaCorner(){

    Position corner1 = new Position(Row.Top, Col.Left);
    Position corner2 =  new Position(Row.Top, Col.Right);
    Position corner3 = new Position(Row.Bottom, Col.Left);
    Position corner4 = new Position(Row.Bottom, Col.Right);

        Board corner_b1 = new Board();
                assertEquals(oppy.getNextMove(corner_b1), (corner1 || corner2 || corner3 || corner4));
}

@Test
    void testPicksWinOverBlocking(){

}

}