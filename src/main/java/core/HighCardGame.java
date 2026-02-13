/*
    NOTE:play

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HighCardGame {

    public int roundsPlayed;
    public Player [] startingPlayers;
    public ArrayList<Player> currentPlayers;

    //initializing a game from list of players.

    public HighCardGame(List<String> initPlayers) {

        this.roundsPlayed = 0;
        this.currentPlayers = new ArrayList<Player>();
        this.startingPlayers = new Player[initPlayers.size()]; //

        //init empty array.
        for (String s : initPlayers) {
            this.currentPlayers.add(Player.makeNewPlayer(s));
        }

        for(int i = 0; i< initPlayers.size(); i++){
            this.startingPlayers[i] = this.currentPlayers.get(i);
        }
    }

    public void dealToAllPlayers() {
        //players should be guaranteed to be only 1-4.
        int numberOfDecks = switch (this.startingPlayers.length) {
            case 2 -> 1;
            case 3, 4 -> 2;
            case 5, 6 -> 3;

            default -> throw new IllegalStateException("Unexpected value, must be between 1-4 players.");
        };

        CardStack gameDeck = new CardStack();
        for(int i = 0; i < numberOfDecks; i++) {
            gameDeck.addToStack(CardStack.newShuffledDeck());
        }

        for (Player p: currentPlayers){
            p.hand = gameDeck.dealHand(26);
        }

    }


    public CardStack everybodyDraws(ArrayList<Player> players){
        CardStack draws = new CardStack();
        for (Player p: currentPlayers){
            draws.addToStack(p.drawsOneCard());
        }
        return(draws); //this is the drawpool.
    }

    //the reason i am returning players is so i can award them a point or if there's a tie, start war between the two.
    public ArrayList<Player> whoWinsRound(CardStack drawPool) { //drawpool is cards TAKEN.

        CardStack winningdraws = drawPool.highestCardsinStack(); //if two players, that's one card
        ArrayList<Player> roundwinners = new ArrayList<>();

        for(int i = 0; i < winningdraws.getNumberOfCards(); i++){ //will only run once.
            for(Card c: drawPool.getCards()){
                if(winningdraws.getCardAt(i) == c){
                    roundwinners.add(currentPlayers.get(drawPool.indexOf(c)));
                    currentPlayers.get(drawPool.indexOf(c)).incScore();
                }
            }
        }
        return(roundwinners);
    }

    //this should always just be a one-item array
    public void giveWinnerCards(ArrayList<Player> winningplayer , CardStack drawPool){
        winningplayer.getFirst().addToHand(drawPool);
    }

    //determines whether a tie occurred depending on length of array
    // representing holders of round's highest cards.
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

    public boolean somebodyWonTheGame(){

        for(Player p: currentPlayers){
            if( p.hand.getNumberOfCards() == startingPlayers.length*26 ){
                break;
            }

            else{return(false);
            }
    }
        return(true);
}


public boolean everyoneHasCards(ArrayList<Player> players) {

        for(Player p: players){
            if (!(p.hasCards())){
                return(false);
            }
        }
        return(true);
}

public void War(CardStack pool, ArrayList<Player> players) {

        if (everyoneHasCards(players))  {
            var newpool = (everybodyDraws(players));
            var winners = whoWinsRound(newpool);
            pool.addToStack(newpool);
            if (winners.size()>1) {
                System.out.println("more than one winner.");
                War(pool,players);
            }
            else {
                giveWinnerCards(winners, pool);
                printEveryonesHand();

            }
        }
    System.out.println("AAAAAAAAAAAAAAAAh");
}

}




