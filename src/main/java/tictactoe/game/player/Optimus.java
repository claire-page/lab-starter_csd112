//package tictactoe.game.player;
//
//import tictactoe.game.Board;
//import tictactoe.game.Position;
//import tictactoe.game.Token;
//
//import java.text.ParseException;
//import java.util.Optional;
//import java.util.Random;
//import java.util.random.RandomGenerator;
//



//managed time poorly so did not complete optimus.
//might come back to this but should focus on test cases and remaining question for now.

//public class Optimus extends Player {
//
//    public Optimus( Token token){
//        super("Optimus", token);
//    }
//
//    public record retval(int score, Position position){}
//
//    public retval minMax (Board b) {
//        //base case.
//       var result = b.getWinner();
//
//       if (result.isPresent()) {
//
//           System.out.println("base case reached! Potential winner located...");
//
//           if (result.get().equals(Optional.of(Token.X))) {
//               var v = (new retval(1, null));
//
//               System.out.println(v);
//               System.out.println(b);
//
//               return(v);
//           }
//          var e = (new retval(-1, null));
//
//           System.out.println(e);
//
//           return(e);
//       }
//
//       if (b.isFull()){ //draw
//
//           System.out.println("draw found...");
//           System.out.println(b);
//
//           return(new retval (0, null));
//       }
//
//       else {
//           System.out.println("recursing....");
//
//        var bestCase = switch(this.token) {
//            case Token.X -> new retval(-1, null);
//            default ->new retval(1, null);
//        };
//
//        for (Position p: b.getEmptyCells()) {
//            Board copy = new Board(b);
//            copy.place(p , this.token);
//
//            System.out.println(copy);
//
//            var recursiveresult = minMax(copy);
//
//            if ((this.token.equals(Token.X) && recursiveresult.score > bestCase.score) || ((this.opponentToken().equals(Token.X))&& recursiveresult.score < bestCase.score)) {
//                bestCase = new retval(recursiveresult.score, p);
//                System.out.println("found a better score!"+ bestCase.score +" is the OG," + recursiveresult.score + "is the new one.");
//            }
//        }
//           System.out.println(bestCase);
//        return(bestCase);
//       }
//    }
//
//    @Override
//    public Position getNextMove(Board board) {
//
//        if (board.isEmpty()) {
//
//            //corners are the best choice for an empty board according to the tictactoe experts out there.
//
//            Position[] cornerPositions ; //just declaring.
//            try {
//                cornerPositions = new Position[]{  Position.parse("top left"),
//                                                Position.parse("top right"),
//                                                Position.parse("bot left"),
//                                                Position.parse("bot right") };
//
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//
//            Random rand = new Random();
//            return(cornerPositions[rand.nextInt(4 )]);
//        }
//        return(minMax(board).position);
//    }
//
//}
//


