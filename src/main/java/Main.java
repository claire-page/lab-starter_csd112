import core.HighCardGame;
import ui.Console;

import java.util.Arrays;
import java.util.List;

public class Main {
    public String header = "     +-+-+-+-+\n H I G H - C A R D\n   java version\n      W-2026\n     +-+-+-+-+\n";

     void main(){

         ui.Console.println(header);

         String [] players = {"A", "B", "C"}; //get input hereeee. limit to 4. idgaf.

         var g = new HighCardGame(getPlayers()); //new game with players.
        //while answers are yes...
         g.dealToAllPlayers();

         //...round....//

             while (goodToGo()){
                 g.printEveryonesHand();
                 var draw = g.createGroupDraw();
                 ui.Console.println(draw.toString());
                 var winners = g.whoWinsRound(draw);
                 System.out.println(Arrays.toString(winners));
                 g.giveWinnerCardsandPoint(draw, winners);
                 g.printEveryonesHand();
                 printStatus(g);

         }
         ui.Console.println("alrighty, farewell.");
         System.exit(0);
    }

    public boolean goodToGo(){
        return(Console.promptForOption("Would you like to play a round?", new String[]{"y", "n"}).equals("y"));
    }

    public List<String> getPlayers(){

         int numberOfPlayers = Integer.parseInt(
                 (Console.promptForOption("How many ppl are playing?", new String[]{"1", "2", "3", "4"})));


         return(Console.promptForNInputs("Enter your name! -> \n", numberOfPlayers));
    }



    public void printStatus(HighCardGame game ){
         ui.Console.println(game.getStatusMsg());
    }
}




//adding to hand works. now.
//for war.
        /*
             Place your main game logic here.
             This is the ONLY code file that should have any reference to the Console class.

             The basic flow of the game is as follows:

             1. Prompt for player names
             2. Deal a shuffled deck evenly to each of the players
             3. While the players have cards and wish to continue:
                 b. All players draw one card and reveal them
                 c. The player with the higher card wins the round (or it's a tie)
         */


