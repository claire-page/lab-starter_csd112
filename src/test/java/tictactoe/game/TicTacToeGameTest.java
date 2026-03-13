package tictactoe.game;
import tictactoe.game.player.*;
import tictactoe.game.TicTacToeGame.*;
import static tictactoe.game.TicTacToeGame.Status.*;


public class TicTacToeGameTest {
    //this is just a method for test purposes to play a game without dealing with all the console output..

    public static Status testGame(Player player1, Player player2) {
        var game = new TicTacToeGame(player1, player2);
        while (game.getStatus() == InProgress) {
            game.doNextTurn();
        }
        return (game.getStatus());
    }

}

