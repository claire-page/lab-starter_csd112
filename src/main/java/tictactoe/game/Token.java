package tictactoe.game;

/**
 * A data type to represent each token on the board
 */
public enum Token { X, O ;

public Token opp(){
    if (this.equals(Token.X)){
        return(Token.O);
    }
    else{
        return(Token.X);
    }
}}
