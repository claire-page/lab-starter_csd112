import core.HighCardGame;
import ui.Console;

import java.util.Arrays;
import java.util.List;

public class Main {
    public String header = "     +-+-+-+-+\n H I G H - C A R D\n   java version\n      W-2026\n     +-+-+-+-+\n";

    void main() {

        ui.Console.println(header);
        var game = new HighCardGame(getPlayers()); //new game with players.
        //while answers are yes...

        game.dealToAllPlayers();

        //...round....//
        while (goodToGo()) {

            var drawPool = game.everybodyDraws(game.currentPlayers);
            ui.Console.println(Arrays.toString(game.startingPlayers));
            ui.Console.println(drawPool.toString());
            var winners = game.whoWinsRound(drawPool);

            System.out.println(winners);
            if (winners.size() > 1) {
                game.War(drawPool, winners);
                printStatus(game);
            } else {
                game.giveWinnerCards(winners, drawPool);
                if (game.somebodyWonTheGame()) {
                    ui.Console.println((winners.getFirst() + "WINS!"));
                } else {
                    game.printEveryonesHand();
                    printStatus(game);
                }
            }
        }

            ui.Console.println("alrighty, farewell.");
            System.exit(0);
        }



        public boolean goodToGo () {
            return (Console.promptForOption
                    ("Would you like to play a round?", new String[]{"y", "n"}).equals("y"));
        }


        public List<String> getPlayers () {

            int numberOfPlayers = Integer.parseInt(
                    (Console.promptForOption("How many ppl are playing?", new String[]{"2", "3", "4", "5"})));


            return (Console.promptForNInputs("Enter your name! -> \n", numberOfPlayers));
        }

        public void printStatus (HighCardGame game){
            ui.Console.println(game.getStatusMsg()); // TEAM CANADA WINS 5-0!
        }


    }


