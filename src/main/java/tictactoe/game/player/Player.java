package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

//turned Player into a class..
public abstract class Player {
     String name;
     Token token;

     //protected constructor.
     protected Player(String name, Token token){
         this.name = name;
         this.token = token;
     }

    public abstract Position getNextMove(Board b);

    public String getName(){
        return(this.name);
    }

    public Token getToken(){
        return(this.token);
    }



}
