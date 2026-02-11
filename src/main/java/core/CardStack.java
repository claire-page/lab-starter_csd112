/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class CardStack {

     private ArrayList<Card> cards;

     private int numberOfCards; //thinking it does not make sense to have this as a var


     private CardStack(ArrayList<Card> cards) { //constructs CardStack from arraylist of cards.
         this.cards = cards;
         this.numberOfCards = cards.size();
    }

    CardStack(){
         this.cards = new ArrayList<Card>();
    }

    public static CardStack newShuffledDeck() {
        var deck = new ArrayList<Card>();

        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                deck.add(new Card(s, r));
            }
        }
        Collections.shuffle(deck);
        return (new CardStack(deck));
    }

    @Override
    public String toString() {
       StringBuilder ret_string = new StringBuilder();
        for (Card c: cards){
            ret_string.append(c.toString());
        }
        return(ret_string).toString();
    }


    public Card drawOneCard(){

         return(cards.removeFirst());
    }

    public CardStack dealHand(int cardsperhands ){
         //only want this to work on a deck, tbh?
         CardStack hand = new CardStack();
         for(int i = 0; i< cardsperhands; i++ ){
        hand.cards.add(i, this.drawOneCard());}
         return hand;
    }

    public Card getCardAt(int index){
         return cards.get(index);
    }

    //for adding a card to a deck. making this overloaded
    public void addToStack (Card c) {
         this.cards.add(c); //this works because the add function appends it to the end of the arraylist.
         this.numberOfCards += 1;
    }

//for adding a deck to another deck.
    public void addToStack (CardStack cStack) {
         while (cStack.getNumberOfCards()>0){
         this.addToStack(cStack.cards.removeFirst());
         this.numberOfCards +=1;}
    }
    //note: this removes.

    public int getNumberOfCards(){

         return(this.cards.size());
    }

//returns CardStack containing highest card(s) in a CardStack. useful for multiplayer methinks.
    // can use it to determine ties i think.
    // i want it to return a new list so I can do comparison and still know whose card was whose.

    //options: 1 start with copy and remove. 2.
    public void replaceStack (Card c){
         this.cards = new ArrayList<Card>();
         this.addToStack(c);

    }

    public void replaceStack (CardStack cStack) {
        this.cards = new ArrayList<Card>();
        this.addToStack(cStack);
    }



//if you run into another one of the same rank, add to the list. if you meet one higher, drop all and replace.
}

//round (players)