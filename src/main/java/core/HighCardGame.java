/*
    NOTE:play

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayList;
import java.util.List;

public class HighCardGame {

    public int roundsPlayed;
    public CardStack drawPool; //wanted to get around making a class for rounds.
    private int numberOfPlayers;
    private Player [] startingPlayers;
    private ArrayList<Player> currentPlayers;

    //initializing a game from list of players,

    public HighCardGame(List<String> initPlayers) {

        this.roundsPlayed = 0;
        this.drawPool = new CardStack();
        this.currentPlayers = new ArrayList<Player>();
        this.startingPlayers = new Player[initPlayers.size()];
        numberOfPlayers = initPlayers.size();


        //init empty array.
        for (String s : initPlayers) {
            this.currentPlayers.add(Player.makeNewPlayer(s));
        }

        for(int i = 0; i< numberOfPlayers; i++){
            this.startingPlayers[i] = this.currentPlayers.get(i);
        }
    }

    public void dealToAllPlayers() {
        //players should be guaranteed to be only 1-4.
        int numberOfDecks = switch (numberOfPlayers) {
            case 1, 2 -> 1;

            case 3, 4 -> 2;

            //the default should never happen but intellijJ was displeased with me for not including it.
            //you never know, ig
            default -> throw new IllegalStateException("Unexpected value: " + numberOfPlayers);
        };

        CardStack gameDeck = new CardStack();
        for(int i = 0; i < numberOfDecks; i++) {
            gameDeck.addToStack(CardStack.newShuffledDeck());
        }

        for (Player p: currentPlayers){
            p.hand = gameDeck.dealHand(26);
        }

    }


    public class GroupDraw {

        public ArrayList<Draw> allDraws;

        //allows you to call whoever. this is used to
        private GroupDraw(Player[] players) {
            this.allDraws = new ArrayList<>();
            for (Player p : players) {
                allDraws.add(new Draw(p, p.drawsOneCard()));
            }
        }


        private GroupDraw() {
            this.allDraws = new ArrayList<>();
            for (Player p : currentPlayers) {
                allDraws.add(new Draw(p, p.drawsOneCard()));
            }
        }

        @Override
        public String toString() {
            StringBuilder retString = new StringBuilder();
            for (Draw d : allDraws) {
                retString.append(d.toString()).append("\n");
            }
            return (retString.toString());
        }


    }

    public Player[] whoWinsRound(GroupDraw groupDraw) {

        ArrayList<Draw> winningdraws = new ArrayList<>(groupDraw.allDraws.size());

        winningdraws.add(groupDraw.allDraws.getFirst());

        for (int j = 1; j < groupDraw.allDraws.size(); j++) {

            Card current = groupDraw.allDraws.get(j).getCard();
            Card currentHighest = winningdraws.getFirst().getCard();

            //if it's the same, add it to the list.
            if ((current.isEqualRank(currentHighest))) {
                winningdraws.add(groupDraw.allDraws.get(j));
            }
            //if you find something bigger, drop and replace.
            else if ((current.isGreaterRank(currentHighest))) {
                winningdraws.clear();
                winningdraws.add(groupDraw.allDraws.get(j));
            }
        }
        //new array containing players that won.
        Player[] winners = new Player[winningdraws.size()];
        for (int k = 0; k < winningdraws.size(); k++) {
            winners[k] = winningdraws.get(k).getPlayer();
        }
        return (winners);
    }


    public GroupDraw createGroupDraw() {
        return (new GroupDraw());
    }

    public void giveWinnerCardsandPoint (GroupDraw groupdraw, Player[] winners) {
        Player winner = winners[0];
        for(Draw d: groupdraw.allDraws){
            winner.addToHand(d.getCard());

        }
        winner.incScore();
    }

    //determines whether a tie occurred depending on length of array representing holders of round's highest cards.

    public boolean isTie(Player[] roundwinners) {
        return ((roundwinners.length) > 1);
    }

    public boolean isGameOver() {
        return ((currentPlayers.size() == 1));
    }

    //kind of a cleanup function ??
    public void eliminateCardlessPlayers() {
        for (Player p : currentPlayers) {
            if (p.getScore() == 0) {
                currentPlayers.remove(p);

            }
        }
    }


//want to call on an array but like. hm.
// public Player[] War (){

//this is just for my testing purposes!
public void printEveryonesHand(){
        for(Player p: currentPlayers){
            System.out.println(p.toString()+ " -> " + p.hand);
        }
    }


    public String getStatusMsg(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Player p: startingPlayers){
            stringBuilder.append(p.toString())
                    .append(" -> ")
                    .append(p.getScore())
                    .append(" rounds won, ")
                    .append(p.hand.getNumberOfCards())
                    .append(" cards in hand.\n");
        }
        return(stringBuilder.toString());
    }

}




