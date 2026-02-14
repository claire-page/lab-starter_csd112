/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

public class Player {

    //these are i-vars.
    private int score;
    private String name;
    private CardStack hand;

    private Player (String inputname){
        this.name = inputname;
        this.score = 0;
        this.hand = new CardStack();//initializes with score of zero.

    }


    public int getScore(){
        return(this.score);
    }

    public String toString(){
        return(this.name);
    }

    public static Player makeNewPlayer(String name){
            return(new Player(name));
    }

    public CardStack getPlayerHand(){
        return(this.hand);
    }

    public Card drawsOneCard(){
        return(this.hand.drawOneCard());
    }

    /**increments score of player by 1.*/
    public void incScore (){
        this.score++;
    }


    public void addToHand (Card c) {
        this.hand.addToStack(c);
    }

    public void addToHand (CardStack cStack) {
        this.hand.addToStack(cStack);
    }

    public void setHandto(CardStack cStack) {
        this.hand = cStack;
    }



    public boolean hasCards (){
        return(this.hand.getNumberOfCards()>0);
    }

}
