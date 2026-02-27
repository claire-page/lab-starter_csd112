package tictactoe.game;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public record Player(String name, Token token,  Attribute playerColor) {

    public String toString () {
        return(colorize(this.name.toString(), this.playerColor));
    }
}


