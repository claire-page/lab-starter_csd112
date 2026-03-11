package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.util.Random;
import java.util.random.RandomGenerator;
//
//public class Optimus extends Player{

//    public Optimus( Token token){
//        super("Optimus", token);
//    }
//
//
//    public record minmaxreturn(int score, Position position){
//
//    }
//
//
//
//    public minmaxreturn minMax (Board b) {
//
//        for (Position p: b.getEmptyCells()){
//
//        }
//
//    }
//
//
//
//    @Override
//    public Position getNextMove(Board board) {
//
//        if (board.isEmpty()){
//
//            //corners are the best choice for an empty board.
//
//            Position[] corners = {  Position.parse("top left"),
//                                    Position.parse("top right"),
//                                    Position.parse("bot left"),
//                                    Position.parse("bot right")  };
//
//            Random rand = new Random();
//            int randomNumber = rand.nextInt(((1 - 5) + 1)+1);
//
//            //returns random corner position from list.
//            return(corners[randomNumber]);
//        }
//        return(minMax.position);
//    }
//
//}



