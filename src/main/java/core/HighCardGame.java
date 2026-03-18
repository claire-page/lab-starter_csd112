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

    private Player [] startingPlayers;
    private ArrayList<Player> currentPlayers;

    //initializing a game from list of players.

    public HighCardGame(List<String> initPlayers) {

        this.currentPlayers = new ArrayList<>();
        this.startingPlayers = new Player[initPlayers.size()]; //

        //init empty array.
        for (String s : initPlayers) {
            this.currentPlayers.add(Player.makeNewPlayer(s));
        }

        for(int i = 0; i< initPlayers.size(); i++){
            this.startingPlayers[i] = this.currentPlayers.get(i);
        }
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**creates appropriate amount of decks and deals 26 cards
     * to each player.
     */
    public void makeDeckandDealToAllPlayers() {
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
            p.setHandto(gameDeck.dealHand(26));
        }

    }

    /**
     *
     * @param players : arraylist of players involved.
     * @return a cardstack containing a card from each player's hand
     */
    public CardStack everybodyDraws(ArrayList<Player> players){
        CardStack draws = new CardStack();
        for (Player p: players){
            if (p.hasCards()){
            draws.addToStack(p.drawsOneCard());}
        }
        return(draws); //this is the drawpool.
    }


    /**
     *
     * @param drawPool the CardStack of drawn cards.
     * @param roundPlayers players involved in the round.
     * @return roundwinners, an ArrayList containing player(s) that who drew highest card(s) in the pool.
     * this is determined using the fact that the index of a card in the pool implies the index of the player
     * that played it.
     */

    //the reason i am returning players is so i can award them a point or if there's a tie, start war between the two.
    public ArrayList<Player> whoWinsRound(CardStack drawPool, ArrayList<Player> roundPlayers) { //drawpool is cards TAKEN.

        var winningRank = drawPool.highestCardsinStack().getCardAt(0).getCardValue(); //if two players, that's one card
        ArrayList<Player> roundwinners = new ArrayList<>();

       int i = 0;
            for(Card c: drawPool.getCards()){
                if(c.getCardValue() == winningRank){
                    roundwinners.add(roundPlayers.get(i));
                }
                i += 1;
            }
        return(roundwinners); //
        }


    /**
     * adds cards to winning player's hand.
     * @param winningplayer - should only contain one item, as the
     * function should only be called if there's one winner.
     * @param drawPool - cards that were involved in the draw.
     */

    public void giveWinnerCards(ArrayList<Player> winningplayer , CardStack drawPool){
        winningplayer.getFirst().addToHand(drawPool);
    }


    /**creates a new ArrayList, adding all current players that have 1+
     cards. reassigns the game's currentPlayers variable to the updated list.
     sort of a cleanup function to ensure that cardless players are
     never included in a draw.
     *
     */
    public void updateActivePlayers(){
      ArrayList<Player>  updatedPlayerList =  new ArrayList<>();
    for (Player p: currentPlayers){
        if(p.hasCards()){
            updatedPlayerList.add(p);
        }
    }
    this.currentPlayers = updatedPlayerList;
    }

    /**
     * building a string for game status.
     * @return string with information on the players' statuses.
     */
    public String getPlayerStatusMsg(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Player p: startingPlayers){
            if(p.hasCards()){
            stringBuilder.append(p)
                    .append(" -> ")
                    .append(p.getScore())
                    .append(" rounds won, ")
                    .append(p.getPlayerHand().getNumberOfCards())
                    .append(" cards in hand.\n");}
            else{
                stringBuilder.append(p)
                        .append("was eliminated and is no longer participating.");
            }
        }
        return(stringBuilder.toString());
    }

    /**
     * checks if anyone has won (if there's only one player remaining)
     * @return boolean representing whether somebody has won the game.
     */

    public boolean somebodyWonTheGame() {
        return(currentPlayers.size()==1); }


    /**
     * sort of a preparation for war, using this mainly to access
     * the war-draw information in the main program.
     * @param tiedPlayers players involved in the war (tied in previous round)
     * @return cardstack of the cards drawn by players for war.
     */
    public CardStack getWarDraw(ArrayList<Player> tiedPlayers) {
    //checking if players can draw another card.
    CardStack warPool = new CardStack();
    for (Player p : tiedPlayers) {
        if (p.hasCards()) {
            warPool.addToStack(p.drawsOneCard());
        }

    }
    return (warPool);}

    //in main, check if it's got anything in it.
    // yes, actually call war.
    //if not, oops. they don't have anymore cards, so update players and move on.

    /**
     * checks for winners.
     * @param warPool cardstack of newly drawn pool of cards by tied players
     * @param initialPool cardstack of the initially drawn pool of cards by all active players
     * @param tiedPlayers arraylist of the players who drew identical value cards and triggered the way
     * @return arraylist containing players which won the war.
     */
       public ArrayList<Player> War(CardStack warPool, CardStack initialPool, ArrayList<Player> tiedPlayers) {

            var winners = whoWinsRound(warPool, tiedPlayers);
            initialPool.addToStack(warPool);

            //checking to see if ONE player won the war,
            //giving them the cards if so.

            if (winners.size()==1) {
                giveWinnerCards(winners, initialPool);
                winners.getFirst().incScore();
                return(winners);
            }
          //no secondary war. nobody recieves a point in the case of no war winners.

            updateActivePlayers();
            return(winners);
        }



public ArrayList<Player> getCurrentPlayers(){
return this.currentPlayers;
}


}




