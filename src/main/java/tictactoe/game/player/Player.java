package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

//turned Player into a class..
public abstract class Player {
     String name;
     Token token;

    public abstract Position getNextMove(Board b);



}
