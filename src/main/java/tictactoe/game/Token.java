package tictactoe.game;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * A data type to represent each token on the board
 */
public enum Token { X(Attribute.BRIGHT_MAGENTA_TEXT(), "X"), O(Attribute.BRIGHT_BLUE_TEXT(), "O");
    final Attribute tokenColor;
    final String tokenString;

    Token(Attribute tokenColor, String tokenString) {
        this.tokenColor = tokenColor;
        this.tokenString = tokenString;
    }

    public String toString(){
        return(colorize(this.tokenString, this.tokenColor));
    }
}



