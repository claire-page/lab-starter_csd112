package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

public class HumanPlayer extends Player {

    public HumanPlayer(String name , Token token){

    super(name, token);

    }

@Override
    public Position getNextMove(Board board) {
        while (true) {
            var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(this.getName(), this.getToken());
            var pos = Console.promptForPosition(prompt, board);

            if (board.isEmptyAt(pos)) {
                return pos;
            }
            Console.printAlert("That position is not valid. Please enter a valid position.");
        }
    }
}
