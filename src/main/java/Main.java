import core.HighCardGame;
import ui.Console;
import core.*;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public String header = "     +-+-+-+-+\n H I G H - C A R D\n   java version\n      W-2026\n     +-+-+-+-+\n";

    void main() {

        ui.Console.println(header);
        var game = new HighCardGame(getPlayers()); //new game with players.
        //while all answers are yes

        game.makeDeckandDealToAllPlayers();

        //play a round!
        while (allPlayersGoodToGo(game.getCurrentPlayers())) {

            var activePlayers = game.getCurrentPlayers();
            var drawPool = game.everybodyDraws(activePlayers);

            ui.Console.println("POOL: " + drawPool.toString());
            var winners = game.whoWinsRound(drawPool, activePlayers);

            if (winners.size() > 1) {
                announceWar(winners);

                var warDraw = game.getWarDraw(winners);

                if (warDraw.getNumberOfCards()>0) {

                    ui.Console.println(warDraw.toString());
                    var warVictor = game.War(warDraw, drawPool, winners);

                    switch (warVictor.size()) {
                        case 1 -> ui.Console.println("Player "+ warVictor.getFirst() + " won the war and got the cards. and a cookie.");
                        case 0 -> ui.Console.println("Aw man, both of the players ran out of cards.");
                        default -> ui.Console.println("Well...looks like a tie, again. Nobody gets a point and we just keep going...");
                    }

                    //there is way too much checking of array length in this code.
                    //but it is submission day.
                    //and i need to submit this.
                    printPlayerStatus(game);

                    if (game.somebodyWonTheGame()) {
                        ui.Console.println((activePlayers.getFirst() + " WINS THE GAME!"));
                    }
                }
                else{
                    ui.Console.println("Aw man, both of the players ran out of cards.");
                }
            } else {
                //if you get here there's def only one winner.
                game.giveWinnerCards(winners, drawPool);
                Console.println(winners.getFirst().toString() + " wins the round!");
                winners.getFirst().incScore();
                game.updateActivePlayers();

                if (game.somebodyWonTheGame()) {
                    ui.Console.println((winners.getFirst() + "WINS THE GAME!"));
                    System.exit(0);
                }
               printPlayerStatus(game);
                }
            }

            ui.Console.println("alrighty, farewell.");
            System.exit(0);
        }

        ///*helper function
        ///prompts all players to confirm they'd like to play
        ///(as per lab instructions).
        ///@return boolean -> false if anyone responds "n" or "N" to the prompt, true otherwise.

        public boolean allPlayersGoodToGo (ArrayList<Player> currentPlayers) {
        for (Player p: currentPlayers){
            if (Console.promptForOption
                    ("Would you like to play a round, "+ p.toString() + "?", new String[]{"y", "n"}).equals("n")){
                     return(false);
                 }
            }
        return(true);
        }

    /**helper function
     * when called, prompts for an integer for number of players (using promptforoption).
     * then prompts for n (numberofPlayers) amount of inputs.
     *
     * @return a list of n (numberofPlayers) Strings of input player names.
     */
    public List<String> getPlayers () {

            int numberOfPlayers = Integer.parseInt(
                    (Console.promptForOption("How many ppl are playing?", new String[]{"2", "3", "4", "5"})));

            return (Console.promptForNInputs("Enter your name! -> \n", numberOfPlayers));
        }

    /**helper function
     * prints status msg retrieved from game status.
     * @param game = instance of a HighCard game.
     */
    public void printPlayerStatus (HighCardGame game){
        ui.Console.println(game.getPlayerStatusMsg());
    }


    /**helper function used to announce war between players.
    * @param tiedPlayers = ArrayList of players that "tied" (all in winners list).
    //prints msg to let user know a tie has occured, and informs of players involved in war.*/
    public void announceWar(ArrayList<Player> tiedPlayers ) {
            StringBuilder stringBuilder = new StringBuilder("Tie between players ");
            stringBuilder.append(tiedPlayers.toString());
            stringBuilder.append("--time for a war!");
            Console.println(stringBuilder.toString());
        }



}



