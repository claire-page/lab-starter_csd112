package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

public abstract class Player {
     String name;
     Token token;

     //protected constructor.
     protected Player(String name, Token token){
         this.name = name;
         this.token = token;
     }


    public String getName(){
        return(this.name);
    }

    public Token getToken(){
        return(this.token);
    }


    public Token opponentToken(){

        if (this.token == Token.X) {
            return(Token.O);
        }
        else{
            return(Token.X);
        }
    }

    public abstract Position getNextMove(Board board);

}
